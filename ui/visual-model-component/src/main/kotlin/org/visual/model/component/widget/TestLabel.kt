package org.visual.model.component.widget

import javafx.scene.control.Label
import javafx.scene.paint.Color

class TestLabel : Label() {

    init {
        text = "test"
        textFill = Color.WHITE
        toFront()
    }
}