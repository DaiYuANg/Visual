package org.visual.model.app.module

import com.google.inject.AbstractModule
import javafx.stage.Stage
import javafx.stage.StageStyle
import org.visual.model.shared.KSlf4j
import org.visual.model.shared.KSlf4j.Companion.log

@KSlf4j
class UIModule : AbstractModule() {
    private val rootStage = Stage().apply {
        centerOnScreen()
        initStyle(StageStyle.TRANSPARENT)
        isResizable = true
    }

    override fun configure() {
        log.info("ui module start")
        bind(Stage::class.java).toInstance(rootStage)
    }
}
