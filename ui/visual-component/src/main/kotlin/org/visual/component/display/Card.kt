package org.visual.component.display

import javafx.geometry.Insets
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Border
import javafx.scene.paint.Color

class Card : SingleChildStackPane() {

  init {
    padding = Insets(10.0)
    border = Border.stroke(Color.ANTIQUEWHITE)
  }

  init {
    addEventHandler(MouseEvent.MOUSE_ENTERED) {
      style = "-fx-background-color: #e0e0e0; -fx-border-color: #000000;"
    }

    addEventHandler(MouseEvent.MOUSE_EXITED) { styleProperty().set("") }
  }
}
