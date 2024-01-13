package org.visual.model.ui.bar

import javafx.geometry.Insets
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.stage.Stage
import org.visual.model.shared.OperationSystem
import org.visual.model.shared.detect
import org.visual.model.ui.util.primaryScreen

abstract class CommonTitleBar : HBox() {
    private var xOffset = 0.0
    private var yOffset = 0.0

    private var prevWidth: Double? = null
    private var prevHeight: Double? = null

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
        addEventHandler(KeyEvent.KEY_TYPED) {
            if (it.code.isFunctionKey && it.code == KeyCode.F11) {
                max(stage)
            }
        }
    }

    fun close() {
        if (detect() == OperationSystem.MAC) {
            stage.isIconified = true;
            return
        }
        this.stage.close()
    }

    fun maximizeWindow() {
        max(stage)
    }

    fun minimizeWindow() {
        stage.isIconified = true
    }

    fun restoreSizeOrMax() {
        if (prevWidth == null && prevHeight == null) {
            prevWidth = scene.width
            prevHeight = scene.height
            max(stage)
        } else {
            stage.width = prevWidth!!
            stage.height = prevHeight!!
        }
    }

    private fun max(stage: Stage) {
        stage.x = primaryScreen.minX
        stage.y = primaryScreen.minY
        stage.width = primaryScreen.width
        stage.height = primaryScreen.height
    }

    protected val stage: Stage
        get() = scene.window as Stage
}
