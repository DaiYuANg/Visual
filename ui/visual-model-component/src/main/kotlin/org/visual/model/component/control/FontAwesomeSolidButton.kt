package org.visual.model.component.control

import javafx.beans.NamedArg
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Button
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon

class FontAwesomeSolidButton
    @JvmOverloads
    constructor(
        @NamedArg("initIcon")
        val initIcon: FontAwesomeSolid? = null,
    ) : Button() {
        private val iconProperty: ObjectProperty<FontAwesomeSolid> by lazy { SimpleObjectProperty() }

        var icon: FontAwesomeSolid?
            get() = iconProperty.get()
            set(value) = iconProperty.set(value)

        init {
            iconProperty.addListener { _, _, newValue ->
                graphic = FontIcon(newValue)
            }
        }

        init {
            if (initIcon !== null) {
                icon = initIcon
            }
        }
    }
