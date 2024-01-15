package org.visual.model.ui.widget;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class FpsWidget extends Label {
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    private final AnimationTimer frameRateMeter = new AnimationTimer() {
        @Override
        public void handle(long now) {
            long oldFrameTime = frameTimes[frameTimeIndex];
            frameTimes[frameTimeIndex] = now;
            frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
            if (frameTimeIndex == 0) {
                arrayFilled = true;
            }
            if (arrayFilled) {
                long elapsedNanos = now - oldFrameTime;
                long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                double frameRate = 1000000000.0 / elapsedNanosPerFrame;
                setText(String.format("Current frame rate: %.3f", frameRate));
            }
        }
    };

    public FpsWidget() {
        frameRateMeter.start();
    }
}
