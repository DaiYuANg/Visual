package org.visual.model.component.control

import atlantafx.base.controls.CustomTextField
import javafx.beans.InvalidationListener
import javafx.beans.property.SimpleObjectProperty
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon
import org.visual.model.component.annotation.FxComponent

@FxComponent
class FontAwesomeIconicTextField : CustomTextField() {

  private val _icon by lazy { SimpleObjectProperty<FontAwesomeSolid>() }
  private val _iconPos by lazy { SimpleObjectProperty(IconicTextFieldPos.LEFT) }

  var icon: FontAwesomeSolid?
    get() = _icon.get()
    set(value) = _icon.set(value)

  @Suppress("unused")
  var iconPos: IconicTextFieldPos?
    get() = _iconPos.get()
    set(value) = _iconPos.set(value)

  init {
    val listener = InvalidationListener {
      val newIcon = _icon.get()
      val newIconPos = _iconPos.get()
      setupIcon(newIcon, newIconPos)
    }
    _icon.addListener(listener)
    _iconPos.addListener(listener)
  }

  private fun setupIcon(icon: FontAwesomeSolid?, iconPos: IconicTextFieldPos?) {
    val fontIcon = FontIcon(icon)
    right = null
    left = null
    when (iconPos) {
      IconicTextFieldPos.LEFT -> left = fontIcon
      IconicTextFieldPos.RIGHT -> right = fontIcon
      else -> {
        throw UnsupportedOperationException()
      }
    }
  }
}
