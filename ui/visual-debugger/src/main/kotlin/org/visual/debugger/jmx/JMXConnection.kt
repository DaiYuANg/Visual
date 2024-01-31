package org.visual.debugger.jmx

import javax.management.JMX
import javax.management.ObjectName
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL
import org.visual.debugger.constant.MBean

class JMXConnection
@JvmOverloads
constructor(
    host: String = "localhost",
    port: Int,
    protocol: String = "service:jmx",
    urlPath: String = "jmxrmi",
    environment: Map<String, Any> = mapOf()
) {
  private val jmxServiceURL =
      JMXServiceURL("${protocol}:rmi:///jndi/rmi://${host}:$port/${urlPath}")
  private val jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, environment)
  private val connector by lazy { JMXConnectorFactory.connect(jmxServiceURL, environment) }
  private val mbeanConn by lazy { jmxConnector.mBeanServerConnection }

  private val availableMbeans by lazy { mbeanConn.queryNames(ObjectName("*:*"), null) }

  fun getMxBean(mBean: MBean) {
    JMX.newMXBeanProxy(mbeanConn, ObjectName(mBean.mbeanName), mBean.associatedClass)
  }
}
