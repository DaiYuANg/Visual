package org.visual.model.debugger.context

import com.sun.tools.attach.VirtualMachine
import io.avaje.inject.BeanScope
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.visual.model.debugger.VisualModelDebugger
import java.nio.charset.StandardCharsets

data object DebuggerContext {
    private val beanScope = BeanScope.builder()
        .shutdownHook(true)
        .build()

    fun <T> get(clazz: Class<T>): T {
        return beanScope.get(clazz)
    }

//    todo why is null when first access
    private val _virtualMachine = SimpleObjectProperty<VirtualMachine>()

    var virtualMachine: VirtualMachine?
        get() = this._virtualMachine.get()
        set(value) = this._virtualMachine.set(value)

    fun addVirtualMachineList(listener: ChangeListener<in VirtualMachine>) {
        _virtualMachine.addListener(listener)
    }

    init {
        addVirtualMachineList { observableValue, t, t2 ->
            run {
                System.err.println(t)
                System.err.println(t2)
                System.err.println(observableValue)
            }
        }
    }

    @SneakyThrows
    fun load(prefix: String): Parent {
        val loader = FXMLLoader(VisualModelDebugger::class.java.getResource("$prefix.fxml"))
        loader.controllerFactory = Callback { aClass: Class<*> ->
            beanScope.get(
                aClass
            )
        }
        loader.charset = StandardCharsets.UTF_8
        return loader.load()
    }

    fun isAttached(): Boolean {
        return this._virtualMachine.isNotNull.get()
    }

    fun isNotAttached(): Boolean {
        return this._virtualMachine.isNull.get()
    }
}