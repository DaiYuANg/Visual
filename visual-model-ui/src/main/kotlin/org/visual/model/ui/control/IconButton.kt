package org.visual.model.ui.control

import javafx.scene.control.Button
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.javafx.FontIcon

class IconButton(icon: Ikon) : Button() {
    private val fontIcon = FontIcon(icon)

    init {
        fontIcon.iconSize = 20
        this.graphic = fontIcon
    }
}
