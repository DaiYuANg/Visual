package org.visual.component.widget;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.visual.component.util.FxUtil;

public class FpsWidget extends Label {
  private final long[] frameTimes = new long[1000];
  private int frameTimeIndex = 0;
  private boolean arrayFilled = false;

  private final Thread t =
      new Thread(
          () -> {
            AnimationTimer frameRateMeter =
                new AnimationTimer() {
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

                      FxUtil.runOnFx(
                          () -> setText(String.format("Current frame rate: %.3f", frameRate)));
                    }
                  }
                };
            frameRateMeter.start();
          });

  public FpsWidget() {
    setTextFill(Color.WHITE);
    toFront();
    t.start();
  }
}
