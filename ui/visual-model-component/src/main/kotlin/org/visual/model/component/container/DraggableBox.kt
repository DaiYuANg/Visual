package org.visual.model.component.container

import javafx.scene.input.MouseEvent
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import org.visual.model.component.util.GeometryUtils
import org.visual.model.component.util.NodeUtil

class DraggableBox : StackPane() {
  private val DEFAULT_ALIGNMENT_THRESHOLD = 5.0

  var lastLayoutX: Double = 0.0

  var lastLayoutY: Double = 0.0

  var lastMouseX: Double = 0.0

  var lastMouseY: Double = 0.0

  var westBoundValue = 15.0

  var eastBoundValue = 15.0

  lateinit var alignmentTargetsX: DoubleArray

  init {
    isPickOnBounds = false
    addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed)
  }

  private fun onMousePressed(event: MouseEvent) {
    val container = NodeUtil.getContainer(this, Region::class.java)
    container?.let {
      val cursorPosition = GeometryUtils.mousePositionToPoint2D(event, it)
      handleDrag(cursorPosition.x, cursorPosition.y)
      event.consume()
    }
  }

  private fun handleDrag(pX: Double, pY: Double) {
    handleDragX(pX)
    //        handleDragY(pY)
    //        // notify
    //        positionMoved()
  }

  private fun handleDragX(pX: Double) {
    val maxParentWidth = parent.layoutBounds.width
    val minLayoutX: Double = westBoundValue
    val maxLayoutX: Double = maxParentWidth - width - eastBoundValue
    val scaleFactor = localToSceneTransform.mxx
    var newLayoutX = lastLayoutX + (pX - lastMouseX) / scaleFactor
    newLayoutX = Math.round(newLayoutX).toDouble().apply { align(this, alignmentTargetsX) }
    layoutX = newLayoutX
  }

  private fun align(position: Double, alignmentValues: DoubleArray): Double {

    return 0.0
  }
}
