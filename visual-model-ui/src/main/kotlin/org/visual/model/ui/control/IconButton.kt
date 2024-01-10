package org.visual.model.ui.control

import javafx.scene.control.Button
import javafx.scene.effect.DropShadow
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.javafx.FontIcon

class IconButton(icon: Ikon) : Button() {
    private val fontIcon = FontIcon(icon)

    init {
        this.graphic = fontIcon

        this.apply {

        }
    }
}
