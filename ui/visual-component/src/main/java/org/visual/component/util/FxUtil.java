package org.visual.component.util;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class FxUtil {
  public void runOnFx(Runnable r) {
    if (Platform.isFxApplicationThread()) {
      r.run();
      return;
    }
    Platform.runLater(r);
  }

  public void runDelay(int millis, Runnable r) {
    var ptr = new AnimationTimer[1];
    ptr[0] =
        new AnimationTimer() {
          private long begin;

          @Override
          public void handle(long now) {
            if (begin == 0) {
              begin = now;
              return;
            }
            if (now - begin < millis * 1_000_000L) {
              return;
            }
            ptr[0].stop();

            Platform.runLater(r);
          }
        };
    ptr[0].start();
  }

  public void forceUpdate(Stage stage) {
    runDelay(
        50,
        () -> {
          var w = stage.getWidth();
          stage.setWidth(w + 0.001);
          runDelay(50, () -> stage.setWidth(w));
        });
  }

  public void forceUpdate(Region region) {
    runDelay(
        50,
        () -> {
          var w = region.getPrefWidth();
          region.setPrefWidth(w + 0.001);
          runDelay(50, () -> region.setPrefWidth(w));
        });
  }

  public @NotNull Rectangle2D calculateTextBounds(@NotNull Label label) {
    val text = new Text(label.getText());
    text.setFont(label.getFont());
    return calculateTextBounds(text);
  }

  @Contract("_ -> new")
  public @NotNull Rectangle2D calculateTextBounds(@NotNull Text text) {
    double textWidth;
    double textHeight;
    {
      textWidth = text.getLayoutBounds().getWidth();
      textHeight = text.getLayoutBounds().getHeight();
    }
    return new Rectangle2D(0, 0, textWidth, textHeight);
  }

  public void showWindow(Window window) {
    try {
      ((Stage) window).setIconified(false);
      ((Stage) window).setAlwaysOnTop(true);
      Platform.runLater(() -> ((Stage) window).setAlwaysOnTop(false));
    } catch (Throwable ignore) {
    }
  }

  public Screen getScreenOf(Window window) {
    if (window == null) return null;
    var screenOb =
        Screen.getScreensForRectangle(
            window.getX(), window.getY(), window.getWidth(), window.getHeight());
    return screenOb.isEmpty() ? Screen.getPrimary() : screenOb.get(0);
  }
}
