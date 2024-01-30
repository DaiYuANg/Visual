package org.visual.component.control

import javafx.scene.control.Dialog
import javafx.scene.input.MouseEvent
import javafx.stage.StageStyle

class UndecoratedDialog<R> : Dialog<R>() {

  private var xOffset = 0.0
  private var yOffset = 0.0

  init {
    initStyle(StageStyle.TRANSPARENT)
    isResizable = true
    dialogPaneProperty().addListener { _, _, _ ->
      run {
        dialogPane.setOnMousePressed(this::onMousePressed)
        dialogPane.setOnMouseDragged(this::onMouseDragged)
        dialogPane.setOnMouseReleased(this::onMouseReleased)
      }
    }
  }

  private fun onMousePressed(event: MouseEvent) {
    xOffset = event.sceneX
    yOffset = event.sceneY
  }

  private fun onMouseDragged(event: MouseEvent) {
    x = event.screenX - xOffset
    y = event.screenY - yOffset
  }

  private fun onMouseReleased(event: MouseEvent) {
    dialogPane.opacity = 1.0
  }
}
