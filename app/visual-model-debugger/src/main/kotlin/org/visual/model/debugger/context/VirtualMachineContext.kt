package org.visual.model.debugger.context

import com.sun.tools.attach.VirtualMachine
import com.sun.tools.attach.VirtualMachineDescriptor
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.visual.model.debugger.util.jmxPropertiesBuilder
import org.visual.model.shared.util.NetUtil.randomPort
import javax.management.MBeanInfo
import javax.management.ObjectName
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL


data object wVirtualMachineContext {


    val virtualMachines: ObservableList<VirtualMachineDescriptor> by lazy {
        FXCollections.synchronizedObservableList(
            FXCollections.observableArrayList(VirtualMachine.list())
        )
    }

    val virtualMachineDescriptor by lazy {
        SimpleObjectProperty<VirtualMachineDescriptor>()
    }

    //    todo why is null when first access
    private val _virtualMachine = SimpleObjectProperty<VirtualMachine>()

    val virtualMachineProperties = Object2ObjectArrayMap<String, String>()

    var virtualMachine: VirtualMachine?
        get() = this._virtualMachine.get()
        set(value) = this._virtualMachine.set(value)

    init {
        addVirtualMachineList { _, oldValue, newValue ->
            run {
                oldValue?.detach()
                setupSystemProperties(newValue)
                setupJMX(newValue)
            }
        }
    }

    private fun setupSystemProperties(newValue: VirtualMachine) {
        virtualMachineProperties.clear().run {
            val propertiesMap =
                newValue.systemProperties.entries.associate { it.key.toString() to it.value.toString() }
            virtualMachineProperties.putAll(propertiesMap)
        }
    }

    private fun setupJMX(newValue: VirtualMachine) {
        val port = randomPort()
        newValue.startManagementAgent(jmxPropertiesBuilder(port = port))
        val url = "service:jmx:rmi:///jndi/rmi://localhost:$port/jmxrmi"
        val serviceUrl = JMXServiceURL(url)
        val jmxConnector = JMXConnectorFactory.connect(serviceUrl, null)
        val mbeanConn = jmxConnector.mBeanServerConnection
        val targetMBean = "java.lang:type=OperatingSystem"
        val objectName = ObjectName(targetMBean)
        val mBeanInfo: MBeanInfo = mbeanConn.getMBeanInfo(objectName)
        println("MBean Name: " + mBeanInfo.className)
        val attributes = mBeanInfo.attributes
        for (attribute in attributes) {
            val attributeName = attribute.name
            val attributeValue: Any = mbeanConn.getAttribute(objectName, attributeName)
            println("$attributeName: $attributeValue")
        }
    }

    fun addVirtualMachineList(listener: ChangeListener<in VirtualMachine>) {
        _virtualMachine.addListener(listener)
    }

    fun isAttached(): Boolean {
        return this._virtualMachine.isNotNull.get()
    }

    fun isNotAttached(): Boolean {
        return this._virtualMachine.isNull.get()
    }

    fun refreshVMS() {
        virtualMachines.clear().run {
            virtualMachines.addAll(VirtualMachine.list())
        }
    }
}