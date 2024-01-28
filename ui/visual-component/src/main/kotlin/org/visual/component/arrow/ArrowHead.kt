package org.visual.component.arrow

import javafx.scene.paint.Color
import javafx.scene.shape.*
import javafx.scene.transform.Rotate

/** An arrow-head shape. This is used by the [Arrow] class. */
class ArrowHead : Path() {
  private var x = 0.0
  private var y = 0.0

  var length = DEFAULT_LENGTH

  /**
   * -- SETTER -- Sets the width of the arrow-head.
   *
   * @param pWidth the width of the arrow-head
   */
  var width = DEFAULT_WIDTH
  var radius = -1.0

  private val rotate = Rotate()

  /** Creates a new [ArrowHead]. */
  init {
    fill = Color.BLACK
    strokeType = StrokeType.INSIDE
    transforms.add(rotate)
  }

  /**
   * Sets the center position of the arrow-head.
   *
   * @param pX the x-coordinate of the center of the arrow-head
   * @param pY the y-coordinate of the center of the arrow-head
   */
  fun setCenter(pX: Double, pY: Double) {
    x = pX
    y = pY

    rotate.pivotX = pX
    rotate.pivotY = pY
  }

  /**
   * Sets the radius of curvature of the [ArcTo] at the base of the arrow-head.
   *
   * If this value is less than or equal to zero, a straight line will be drawn instead. The default
   * is -1.
   *
   * @param pRadius the radius of curvature of the arc at the base of the arrow-head
   */
  fun setRadiusOfCurvature(pRadius: Double) {
    radius = pRadius
  }

  /**
   * Sets the rotation angle of the arrow-head.
   *
   * @param angle the rotation angle of the arrow-head, in degrees
   */
  fun setAngle(angle: Double) {
    rotate.angle = angle
  }

  /** Draws the arrow-head for its current size and position values. */
  fun draw() {
    elements.clear()

    elements.add(MoveTo(x, y + length / 2))
    elements.add(LineTo(x + width / 2, y - length / 2))

    if (radius > 0) {
      val arcTo = ArcTo()
      arcTo.x = x - width / 2
      arcTo.y = y - length / 2
      arcTo.radiusX = radius
      arcTo.radiusY = radius
      arcTo.isSweepFlag = true
      elements.add(arcTo)
    } else {
      elements.add(LineTo(x - width / 2, y - length / 2))
    }

    elements.add(ClosePath())
  }

  companion object {
    private const val DEFAULT_LENGTH = 10.0
    private const val DEFAULT_WIDTH = 10.0
  }
}
