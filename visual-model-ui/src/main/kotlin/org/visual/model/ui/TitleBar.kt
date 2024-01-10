package org.visual.model.ui

import javafx.geometry.Insets
import javafx.geometry.Rectangle2D
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.stage.Screen
import javafx.stage.Stage
import org.apache.commons.lang3.SystemUtils

open class TitleBar : HBox() {
    private val bounds: Rectangle2D = Screen.getPrimary().visualBounds

    private var xOffset = 0.0
    private var yOffset = 0.0

    init {
        addEventHandler(MouseEvent.MOUSE_DRAGGED) { e: MouseEvent ->
            scene.window.x = e.screenX - xOffset
            scene.window.y = e.screenY - yOffset
        }
        addEventHandler(MouseEvent.MOUSE_PRESSED) { e: MouseEvent ->
            scene.window.opacity = 0.5
            xOffset = e.sceneX
            yOffset = e.sceneY
        }
        addEventHandler(MouseEvent.MOUSE_RELEASED) { e: MouseEvent? ->
            scene.window.opacity = 1.0
        }
        padding = Insets(0.0, 0.0, 0.0, 0.0)
    }

    protected fun close() {
        val stage = stage
        if (SystemUtils.IS_OS_MAC) {
            stage.isIconified = true
            return
        }
        this.stage.close()
    }

    protected fun maximizeWindow(mouseEvent: MouseEvent) {
        val stage = stage
        if (mouseEvent.clickCount == 2) {
            max(stage)
            stage.isFullScreen = true
            return
        }
        max(stage)
    }

    protected fun minimizeWindow(mouseEvent: MouseEvent?) {
        stage.isIconified = true
    }

    private fun max(stage: Stage) {
        stage.x = bounds.minX
        stage.y = bounds.minY
        stage.width = bounds.width
        stage.height = bounds.height
    }

    private val stage: Stage
        get() = scene.window as Stage
}
