package org.visual.model.app.module

import com.google.inject.AbstractModule
import javafx.stage.Stage
import javafx.stage.StageStyle

class UIModule : AbstractModule() {
    private val rootStage = Stage().apply {
        centerOnScreen()
        initStyle(StageStyle.TRANSPARENT)
        isResizable = true
    }

    override fun configure() {
        bind(Stage::class.java).toInstance(rootStage)
    }
}
