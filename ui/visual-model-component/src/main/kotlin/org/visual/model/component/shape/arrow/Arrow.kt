package org.visual.model.component.shape.arrow

import javafx.geometry.Point2D
import javafx.scene.Group
import javafx.scene.shape.Line
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import org.visual.model.component.util.GeometryUtils

/**
 * An arrow shape.
 *
 * This is a [Node] subclass and can be added to the JavaFX scene graph in the usual way. Styling
 * can be achieved via the CSS classes *arrow-line* and *arrow-head*.
 *
 * Example:
 * <pre>
 * `Arrow arrow = new Arrow();
 * arrow.setStart(10, 20);
 * arrow.setEnd(100, 150);
 * arrow.draw();`
 * </pre> *
 */
class Arrow : Group() {
  private val line = Line()
  private val head = ArrowHead()

  private var startX = 0.0
  private var startY = 0.0

  private var endX = 0.0
  private var endY = 0.0

  /** Creates a new [Arrow]. */
  init {
    line.styleClass.add(STYLE_CLASS_LINE)
    head.styleClass.add(STYLE_CLASS_HEAD)

    children.addAll(line, head)
  }

  /**
   * Sets the width of the arrow-head.
   *
   * @param width the width of the arrow-head
   */
  fun setHeadWidth(width: Double) {
    head.width = width
  }

  /**
   * Sets the length of the arrow-head.
   *
   * @param length the length of the arrow-head
   */
  fun setHeadLength(length: Double) {
    head.length = length
  }

  /**
   * Sets the radius of curvature of the [ArcTo] at the base of the arrow-head.
   *
   * If this value is less than or equal to zero, a straight line will be drawn instead. The default
   * is -1.
   *
   * @param radius the radius of curvature of the arc at the base of the arrow-head
   */
  fun setHeadRadius(radius: Double) {
    head.setRadiusOfCurvature(radius)
  }

  val start: Point2D
    /**
     * Gets the start point of the arrow.
     *
     * @return the start [Point2D] of the arrow
     */
    get() = Point2D(startX, startY)

  /**
   * Sets the start position of the arrow.
   *
   * @param pStartX the x-coordinate of the start position of the arrow
   * @param pStartY the y-coordinate of the start position of the arrow
   */
  fun setStart(pStartX: Double, pStartY: Double) {
    startX = pStartX
    startY = pStartY
  }

  val end: Point2D
    /**
     * Gets the start point of the arrow.
     *
     * @return the start [Point2D] of the arrow
     */
    get() = Point2D(endX, endY)

  /**
   * Sets the end position of the arrow.
   *
   * @param pEndX the x-coordinate of the end position of the arrow
   * @param pEndY the y-coordinate of the end position of the arrow
   */
  fun setEnd(pEndX: Double, pEndY: Double) {
    endX = pEndX
    endY = pEndY
  }

  /** Draws the arrow for its current size and position values. */
  fun draw() {
    val deltaX = endX - startX
    val deltaY = endY - startY

    val angle = atan2(deltaX, deltaY)

    val headX = endX - head.length / 2 * sin(angle)
    val headY = endY - head.length / 2 * cos(angle)

    line.startX = GeometryUtils.moveOffPixel(startX)
    line.startY = GeometryUtils.moveOffPixel(startY)
    line.endX = GeometryUtils.moveOffPixel(headX)
    line.endY = GeometryUtils.moveOffPixel(headY)

    head.setCenter(headX, headY)
    head.setAngle(Math.toDegrees(-angle))
    head.draw()
  }

  companion object {
    private const val STYLE_CLASS_LINE = "arrow-line"
    private const val STYLE_CLASS_HEAD = "arrow-head"
  }
}
