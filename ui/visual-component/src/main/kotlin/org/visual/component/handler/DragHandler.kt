package org.visual.component.handler

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

/**
 * The drag handler<br></br> Usage example:
 * <pre>
 * var handler = new DragHandler() {
 * // ... override ...
 * };
 * node.setOnMousePressed(handler);
 * node.setOnMouseDragged(handler);
 * </pre> *
 */
abstract class DragHandler : EventHandler<MouseEvent> {
  private var oldNodeX = 0.0
  private var oldNodeY = 0.0
  private var oldOffsetX = 0.0
  private var oldOffsetY = 0.0

  protected abstract fun set(x: Double, y: Double)

  protected abstract fun get(): DoubleArray

  private fun getOffset(e: MouseEvent): DoubleArray {
    return doubleArrayOf(e.screenX, e.screenY)
  }

  override fun handle(e: MouseEvent) {
    if (e.eventType == MouseEvent.MOUSE_PRESSED) {
      pressed(e)
    } else if (e.eventType == MouseEvent.MOUSE_DRAGGED) {
      dragged(e)
      consume(e)
    }
  }

  protected open fun consume(e: MouseEvent) {
    // do not consume
  }

  /**
   * The function to run when pressed
   *
   * @param e mouse event
   */
  protected fun pressed(e: MouseEvent) {
    val xy = get()
    this.oldNodeX = xy[0]
    this.oldNodeY = xy[1]
    val offsetXy = getOffset(e)
    oldOffsetX = offsetXy[0]
    oldOffsetY = offsetXy[1]
  }

  /**
   * The function to run when dragged
   *
   * @param e mouse event
   */
  protected fun dragged(e: MouseEvent) {
    val offxy = getOffset(e)
    val deltaX = offxy[0] - this.oldOffsetX
    val deltaY = offxy[1] - this.oldOffsetY
    val x = calculateDeltaX(deltaX, deltaY) + this.oldNodeX
    val y = calculateDeltaY(deltaX, deltaY) + this.oldNodeY
    set(x, y)
  }

  /**
   * Calculate actual delta X to apply
   *
   * @param deltaX raw deltaX
   * @param deltaY raw deltaY
   * @return deltaX to apply
   */
  protected open fun calculateDeltaX(deltaX: Double, @Suppress("unused") deltaY: Double): Double {
    return deltaX
  }

  /**
   * Calculate actual delta Y to apply
   *
   * @param deltaX raw deltaX
   * @param deltaY raw deltaY
   * @return deltaY to apply
   */
  protected fun calculateDeltaY(@Suppress("unused") deltaX: Double, deltaY: Double): Double {
    return deltaY
  }
}
