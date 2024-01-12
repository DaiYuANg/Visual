package org.visual.model.ui.widget

import javafx.animation.AnimationTimer
import javafx.scene.control.Label


class FpsWidget : Label() {
    private val frameTimes = LongArray(100)
    private var frameTimeIndex = 0
    private var arrayFilled = false
    private val frameRateMeter = object : AnimationTimer() {
        override fun handle(now: Long) {
            val oldFrameTime = frameTimes[frameTimeIndex]
            frameTimes[frameTimeIndex] = now
            frameTimeIndex = (frameTimeIndex + 1) % frameTimes.size;
            if (frameTimeIndex == 0) {
                arrayFilled = true;
            }
            if (arrayFilled) {
                val elapsedNanos = now - oldFrameTime
                val elapsedNanosPerFrame: Long = elapsedNanos / frameTimes.size
                val frameRate = 1000000000.0 / elapsedNanosPerFrame
                this@FpsWidget.text = String.format("Current frame rate: %.3f", frameRate)
            }
        }
    }

    init {
        frameRateMeter.start();
    }
}
