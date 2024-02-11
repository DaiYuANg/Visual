package org.visual.component.display

import javafx.event.EventHandler
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.visual.component.control.FontAwesomeSolidButton

class MacOSTitleBar : CommonTitleBar() {
  private val closeButton = FontAwesomeSolidButton(FontAwesomeSolid.BARS)
  private val sizeableButton = FontAwesomeSolidButton(FontAwesomeSolid.MINUS)
  private val minimizeButton = FontAwesomeSolidButton(FontAwesomeSolid.CLOSED_CAPTIONING)

  init {
    closeButton.onAction = EventHandler { close() }

    sizeableButton.onMouseClicked = EventHandler { event: MouseEvent ->
      if (event.button == MouseButton.PRIMARY && event.clickCount == 2) {
        restoreSizeOrMax()
      } else {
        maximize()
      }
    }

    minimizeButton.onAction = EventHandler { maximize() }
    children.addAll(closeButton, sizeableButton, minimizeButton)
  }
}
