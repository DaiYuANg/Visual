package org.visual.component.control

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Button
import org.kordamp.ikonli.fluentui.FluentUiRegularAL
import org.kordamp.ikonli.javafx.FontIcon

class FluentUiRegularALButton : Button() {
  private val iconProperty: ObjectProperty<FluentUiRegularAL> by lazy { SimpleObjectProperty() }

  var icon: FluentUiRegularAL?
    get() = iconProperty.get()
    set(value) = iconProperty.set(value)

  init {
    iconProperty.addListener { _, _, newValue -> graphic = FontIcon(newValue) }
  }
}
