package org.visual.model.app.module

import com.google.inject.AbstractModule
import org.github.gestalt.config.Gestalt
import org.github.gestalt.config.kotlin.getConfig
import org.visual.model.app.config.UI


class ConfigModule(
    private val gestalt: Gestalt
) : AbstractModule() {

    override fun configure() {
        val ui: UI = gestalt.getConfig("ui")
        System.err.println(ui)
    }
}