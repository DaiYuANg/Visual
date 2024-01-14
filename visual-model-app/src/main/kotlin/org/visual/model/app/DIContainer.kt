package org.visual.model.app

import com.google.inject.Guice
import com.google.inject.Injector
import org.github.gestalt.config.Gestalt
import org.github.gestalt.config.builder.GestaltBuilder
import org.github.gestalt.config.guice.GestaltModule
import org.github.gestalt.config.loader.EnvironmentVarsLoader
import org.github.gestalt.config.loader.MapConfigLoader
import org.github.gestalt.config.loader.PropertyLoader
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder
import org.github.gestalt.config.source.ConfigSourcePackage
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder
import org.github.gestalt.config.toml.TomlLoader
import org.visual.model.app.module.*

private const val defaultConfig = "visual.model.properties"

private val configLoaders = listOf(EnvironmentVarsLoader(), TomlLoader(), PropertyLoader(), MapConfigLoader())

private val parentDir = VisualModelUIApplication::class.java.packageName

private val configFileName = "visual.model.toml"
private val environmentSource = EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL_MODEL")
    .setFailOnErrors(false).build()
private val classPathSource = ClassPathConfigSourceBuilder.builder().setResource(defaultConfig).build()
private val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build()
private val gestaltInstance: Gestalt = GestaltBuilder().apply {
    this.useCacheDecorator(true)
    this.addConfigLoaders(configLoaders)
    listOf(classPathSource, environmentSource, systemSource)
        .forEach<ConfigSourcePackage?>(this::addSource)
}.build().run {
    this.loadConfigs()
    this
}

object DIContainer {
    private val rootModule by lazy { RootModule() }

    private val simpleModule by lazy { SimpleModule() }

    private val configModule by lazy { ConfigModule(gestaltInstance) }

    private val gestaltModule by lazy {
        GestaltModule(gestaltInstance)
    }

    val injector: Injector by lazy {
        Guice.createInjector(
            rootModule,
            simpleModule,
            gestaltModule,
            configModule
        )
    }
}
