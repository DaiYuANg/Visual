package org.visual.component.control

import atlantafx.base.controls.CustomTextField
import java.util.*
import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.beans.property.SimpleObjectProperty
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon

class FontAwesomeIconicTextField : CustomTextField() {
  private val _icon = SimpleObjectProperty<FontAwesomeSolid>()

  var icon: FontAwesomeSolid
    get() = _icon.get()
    set(value) {
      _icon.set(value)
    }

  private val _iconPos = SimpleObjectProperty(IconicPos.LEFT)

  @get:Suppress("unused")
  @set:Suppress("unused")
  var iconPos: IconicPos
    get() = _iconPos.get()
    set(value) {
      _iconPos.set(value)
    }

  init {
    val listener = InvalidationListener { b: Observable? ->
      val newIcon = _icon.get()
      val newIconPos = _iconPos.get()
      setupIcon(newIcon, newIconPos)
    }
    _icon.addListener(listener)
    _iconPos.addListener(listener)
  }

  private fun setupIcon(icon: FontAwesomeSolid, iconPos: IconicPos) {
    val fontIcon = FontIcon(icon)
    right = null
    left = null
    when (iconPos) {
      IconicPos.LEFT -> left = fontIcon
      IconicPos.RIGHT -> right = fontIcon
    }
  }

  fun getOnEventClick(handler: EventHandler<in MouseEvent?>): EventHandler<in MouseEvent?> {
    val item = Optional.ofNullable(left).orElse(right)
    item.onMouseClicked = handler
    return handler
  }
}
