package org.visual.component.container.draggable

import javafx.scene.input.MouseEvent
import javafx.scene.layout.Region
import org.visual.component.util.getContainer
import org.visual.component.util.mousePositionToPoint2D

class DraggableBox : BaseDraggableBox() {

  init {
    addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed)
    addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDragged)
    addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased)
  }

  private fun onMousePressed(event: MouseEvent) {
    val container = getContainer(this, Region::class.java)
    val cursorPosition = container?.let { mousePositionToPoint2D(event, it) }
    cursorPosition?.let {
      storeClickValuesForDrag(it.x, it.y)
      event.consume()
    }
  }

  private fun onMouseDragged(event: MouseEvent) {
    val container = getContainer(this, Region::class.java)
    val cursorPosition = container?.let { mousePositionToPoint2D(event, it) }

    cursorPosition?.let {
      dragX(it.x)

      positionMoved()
      event.consume()
    }
  }

  private fun dragX(pX: Double) {
    val minLayoutX: Double = boundValue
    val maxLayoutX: Double = parent.layoutBounds.width - width - boundValue
    var newLayoutX: Double = lastLayoutX + (pX - lastMouseX) / localToSceneTransform.mxx
    newLayoutX = Math.round(newLayoutX).toDouble()
    when {
      newLayoutX < minLayoutX -> {
        newLayoutX = minLayoutX
      }
      newLayoutX > maxLayoutX -> {
        newLayoutX = maxLayoutX
      }
    }
    layoutX = newLayoutX
  }

  private fun dragY(pY: Double) {
    val minLayoutY: Double = boundValue
    val maxLayoutY: Double = parent.layoutBounds.height - height - boundValue
    var newLayoutY: Double = lastLayoutY + (pY - lastMouseY) / localToSceneTransform.mxx

    newLayoutY = Math.round(newLayoutY).toDouble()
    when {
      newLayoutY < minLayoutY -> {
        newLayoutY = minLayoutY
      }
      newLayoutY > maxLayoutY -> {
        newLayoutY = maxLayoutY
      }
    }
    layoutY = newLayoutY
  }

  private fun onMouseReleased(event: MouseEvent) {
    event.consume()
  }

  private fun positionMoved() {}
}
