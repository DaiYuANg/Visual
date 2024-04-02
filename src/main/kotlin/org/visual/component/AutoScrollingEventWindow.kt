package org.visual.component

import javafx.animation.Timeline
import javafx.geometry.Point2D
import javafx.util.Duration
import kotlin.math.min

class AutoScrollingEventWindow : PanningEventWindow() {
  private val JUMP_PERIOD: Duration = Duration.millis(25.0)

  private val baseJumpAmount = 1.0
  private val maxJumpAmount = 50.0
  private val jumpAmountIncreasePerJump = 0.5
  private val insetToBeginScroll = 1.0

  private var timeline: Timeline? = null
  private var isScrolling = false
  private var jumpDistance: Point2D? = null

  private val autoScrollingEnabled = true
  private var jumpsTaken = 0

  init {
    //    addEventHandler(MouseEvent.MOUSE_DRAGGED) {
    //      jumpDistance = getDistanceToJump(it.x, it.y)
    //      if (jumpDistance == null) {
    //        jumpsTaken = 0
    //      } else if (!isScrolling && autoScrollingEnabled) {
    //        startScrolling()
    //      }
    //    }
  }

  /** Starts the auto-scrolling. */
  private fun startScrolling() {
    //    isScrolling = true
    //    jumpsTaken = 0
    //
    //    val frame = KeyFrame(JUMP_PERIOD,
    //      { _: ActionEvent? ->
    //        if (isScrolling && jumpDistance != null) {
    ////          panBy(jumpDistance!!.x, jumpDistance!!.y)
    //          jumpsTaken++
    //        }
    //      })
    //
    //    timeline = Timeline()
    //    timeline!!.cycleCount = Animation.INDEFINITE
    //    timeline!!.keyFrames.add(frame)
    //    timeline!!.play()
  }

  //  private fun panBy(x: Double, y: Double) {
  //    if (x != 0.0 && y != 0.0) {
  //      panTo(contentX.get() + x, contentY.get() + y)
  //    } else if (x != 0.0) {
  //      panToX(contentX.get() + x)
  //    } else if (y != 0.0) {
  //      panToY(contentY.get() + y)
  //    }
  //  }

  private fun getDistanceToJump(cursorX: Double, cursorY: Double): Point2D? {
    var jumpX = 0.0
    var jumpY = 0.0

    val baseAmount = baseJumpAmount
    val additionalAmount = jumpsTaken * jumpAmountIncreasePerJump
    val distance = min(baseAmount + additionalAmount, maxJumpAmount)

    if (cursorX <= insetToBeginScroll) {
      jumpX = -distance
    } else if (cursorX >= width - insetToBeginScroll) {
      jumpX = distance
    }

    if (cursorY <= insetToBeginScroll) {
      jumpY = -distance
    } else if (cursorY >= height - insetToBeginScroll) {
      jumpY = distance
    }

    if (jumpX == 0.0 && jumpY == 0.0) {
      return null
    }
    return Point2D(Math.round(jumpX).toDouble(), Math.round(jumpY).toDouble())
  }
}
