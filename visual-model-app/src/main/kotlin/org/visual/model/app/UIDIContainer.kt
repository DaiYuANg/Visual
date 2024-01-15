package org.visual.model.app

import com.google.inject.ConfigurationException
import com.google.inject.Guice
import com.google.inject.Injector
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import org.visual.model.app.module.UIModule
import java.nio.charset.StandardCharsets

object UIDIContainer {
    private val uiModule by lazy { UIModule() }

    val injector: Injector = Guice.createInjector(uiModule)

    fun load(prefix: String): Parent {
        val loader = FXMLLoader(VisualModuleApplication::class.java.getResource("$prefix.fxml"))
        loader.controllerFactory = Callback {
            try {
                DIContainer.injector.getInstance(it)
            } catch (ex: ConfigurationException) {
                injector.getInstance(it)
            }
        }
//        loader.controllerFactory = Callback { type: Class<*>? -> DIContainer.injector.getInstance(type) }
        loader.charset = StandardCharsets.UTF_8
        return loader.load()
    }
}