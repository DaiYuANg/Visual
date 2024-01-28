package org.visual.component.display

import javafx.stage.Stage

class VisualStage : Stage() {

  fun showAndFocus() {
    requestFocus()
    show()
  }
}
