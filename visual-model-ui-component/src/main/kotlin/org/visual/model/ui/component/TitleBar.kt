package org.visual.model.ui.component

import javafx.geometry.Rectangle2D
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.stage.Screen

class TitleBar : HBox() {

    private val window = parent.scene.window
    private val xOffset = 0.0
    private val yOffset = 0.0

    init {
        addEventHandler(MouseEvent.MOUSE_DRAGGED) {
            window.x = it.sceneX - xOffset
            window.y = it.screenY - yOffset
        }
    }

    init {
        addEventHandler(MouseEvent.MOUSE_PRESSED) {
            this.scene.window.opacity = 0.5
        }
    }

    init {
        addEventHandler(MouseEvent.MOUSE_RELEASED) {
            window.opacity = 1.0
        }
    }
}