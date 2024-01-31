package org.visual.debugger.context

import com.google.inject.Guice
import com.google.inject.Injector
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.github.gestalt.config.builder.GestaltBuilder
import org.github.gestalt.config.guice.GestaltModule
import org.github.gestalt.config.loader.EnvironmentVarsLoader
import org.github.gestalt.config.loader.MapConfigLoader
import org.github.gestalt.config.loader.PropertyLoader
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder
import org.github.gestalt.config.source.ConfigSourcePackage
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder
import org.visual.debugger.component.VisualDebugger
import org.visual.debugger.module.ConfigModule
import org.visual.debugger.module.JvmFactory
import org.visual.debugger.module.UIFactory
import java.nio.charset.StandardCharsets

data object DebuggerContext {

  private val configModule by lazy {
    ConfigModule()
  }

  private val configLoaders =
    listOf(EnvironmentVarsLoader(), PropertyLoader(), MapConfigLoader())

  private val environmentSource: ConfigSourcePackage =
    EnvironmentConfigSourceBuilder.builder()
      .setPrefix("VISUAL_MODEL")
      .setFailOnErrors(false)
      .build()

  private val classPathSource =
    ClassPathConfigSourceBuilder.builder()
      .setResource("visual.model.debugger.properties")
      .build()

  private val javafxClassPathSource =
    ClassPathConfigSourceBuilder.builder().setResource("javafx.properties").build()

  private val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build()

  private val gestaltModule by lazy {
    val builder = GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders)
    builder.addSources(
      listOf(classPathSource, environmentSource, systemSource, javafxClassPathSource)
    )
    val gestalt = builder.build()
    gestalt.loadConfigs()
    GestaltModule(gestalt)
  }

  private val JvmFactory by lazy { JvmFactory() }

  private val uiFactory by lazy { UIFactory() }

  private val injector: Injector = Guice.createInjector(configModule, gestaltModule, JvmFactory, uiFactory)

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
