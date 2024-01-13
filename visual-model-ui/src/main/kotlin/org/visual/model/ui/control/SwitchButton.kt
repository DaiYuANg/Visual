package org.visual.model.ui.control

import javafx.scene.control.ToggleButton
import javafx.scene.layout.StackPane

class SwitchButton : StackPane() {
    private val switchButton: ToggleButton by lazy { ToggleButton() }

    init {
        children.add(switchButton)
        switchButton.styleClass.add("custom-switch");
        stylesheets.add("switchButton.css");
    }
}