package org.visual.model.component.control

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Button
import org.kordamp.ikonli.fluentui.FluentUiRegularMZ
import org.kordamp.ikonli.javafx.FontIcon
import org.visual.model.component.annotation.FxComponent

@FxComponent
class FluentUiRegularMZButton : Button() {
  private val iconProperty: ObjectProperty<FluentUiRegularMZ> by lazy { SimpleObjectProperty() }

  var icon: FluentUiRegularMZ?
    get() = iconProperty.get()
    set(value) = iconProperty.set(value)

  init {
    iconProperty.addListener { _, _, newValue -> graphic = FontIcon(newValue) }
  }
}
