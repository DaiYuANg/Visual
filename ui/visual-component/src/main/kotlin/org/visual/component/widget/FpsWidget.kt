package org.visual.component.widget

import javafx.animation.AnimationTimer
import javafx.scene.control.Label
import javafx.scene.paint.Color
import org.visual.component.util.runOnFX

class FpsWidget : Label() {
  private val frameTimes = LongArray(1000)
  private var frameTimeIndex = 0
  private var arrayFilled = false

  private val t =
      Thread.ofPlatform().name("fps", 0).unstarted {
        val frameRateMeter =
            object : AnimationTimer() {
              override fun handle(now: Long) {
                val oldFrameTime = frameTimes[frameTimeIndex]
                frameTimes[frameTimeIndex] = now
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.size
                if (frameTimeIndex == 0) {
                  arrayFilled = true
                }
                if (arrayFilled) {
                  val elapsedNanos = now - oldFrameTime
                  val elapsedNanosPerFrame = elapsedNanos / frameTimes.size
                  val frameRate = 1000000000.0 / elapsedNanosPerFrame

                  runOnFX { text = String.format("Current frame rate: %.3f", frameRate) }
                }
              }
            }

        frameRateMeter.start()
      }
  //
  //  private val frameRateMeter: AnimationTimer =
  //      object : AnimationTimer() {
  //        override fun handle(now: Long) {
  //          val oldFrameTime = frameTimes[frameTimeIndex]
  //          frameTimes[frameTimeIndex] = now
  //          frameTimeIndex = (frameTimeIndex + 1) % frameTimes.size
  //          if (frameTimeIndex == 0) {
  //            arrayFilled = true
  //          }
  //          if (arrayFilled) {
  //            val elapsedNanos = now - oldFrameTime
  //            val elapsedNanosPerFrame = elapsedNanos / frameTimes.size
  //            val frameRate = 1000000000.0 / elapsedNanosPerFrame
  //            text = String.format("Current frame rate: %.3f", frameRate)
  //          }
  //        }
  //      }

  init {
    textFill = Color.WHITE
    toFront()
    t.start()
    //    frameRateMeter.start()
  }
}
