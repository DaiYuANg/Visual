package org.visual.debugger.context

import com.google.inject.Guice
import com.google.inject.Injector
import java.nio.charset.StandardCharsets
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.visual.debugger.VisualDebugger
import org.visual.debugger.view.VisualDebuggerView
import org.visual.debugger.module.ConfigModule
import org.visual.debugger.module.JvmFactory
import org.visual.debugger.module.UIFactory

data object DebuggerContext {

  private val configModule by lazy {
    ConfigModule()
  }

  private val JvmFactory by lazy { JvmFactory() }

  private val uiFactory by lazy { UIFactory() }

  private val injector: Injector = Guice.createInjector(configModule, JvmFactory, uiFactory)

  fun <T> get(clazz: Class<T>): T {
    return injector.getInstance(clazz)
  }

  @SneakyThrows
  fun load(prefix: String): Parent {
    val loader =
        FXMLLoader(VisualDebugger::class.java.getResource("$prefix.fxml")).apply {
          controllerFactory = Callback { aClass: Class<*> -> injector.getInstance(aClass) }
          charset = StandardCharsets.UTF_8
        }
    return loader.load()
  }
}
