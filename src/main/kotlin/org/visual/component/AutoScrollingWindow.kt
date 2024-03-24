package org.visual.component

import javafx.animation.Timeline
import javafx.geometry.Point2D
import javafx.util.Duration

class AutoScrollingWindow : PanningWindow() {
  private val JUMP_PERIOD: Duration = Duration.millis(25.0)

  private val baseJumpAmount = 1.0
  private val maxJumpAmount = 50.0
  private val jumpAmountIncreasePerJump = 0.5
  private val insetToBeginScroll = 1.0

  private val timeline: Timeline? = null
  private val isScrolling = false
  private val jumpDistance: Point2D? = null

  private val autoScrollingEnabled = true
  private val jumpsTaken = 0
}
