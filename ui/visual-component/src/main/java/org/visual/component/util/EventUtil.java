package org.visual.component.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class EventUtil {
  public void setOnMouseEntered(@NotNull Node node, EventHandler<? super MouseEvent> f) {
    var ff = node.getOnMouseEntered();
    if (ff == null) {
      node.setOnMouseEntered(f);
    } else {
      node.setOnMouseEntered(
          e -> {
            f.handle(e);
            ff.handle(e);
          });
    }
  }

  public void setOnMouseExited(@NotNull Node node, EventHandler<? super MouseEvent> f) {
    var ff = node.getOnMouseExited();
    if (ff == null) {
      node.setOnMouseExited(f);
    } else {
      node.setOnMouseExited(
          e -> {
            f.handle(e);
            ff.handle(e);
          });
    }
  }

  public void setOnMousePressed(@NotNull Node node, EventHandler<? super MouseEvent> f) {
    var ff = node.getOnMousePressed();
    if (ff == null) {
      node.setOnMousePressed(f);
    } else {
      node.setOnMousePressed(
          e -> {
            f.handle(e);
            ff.handle(e);
          });
    }
  }

  public void setOnMouseReleased(@NotNull Node node, EventHandler<? super MouseEvent> f) {
    var ff = node.getOnMouseReleased();
    if (ff == null) {
      node.setOnMouseReleased(f);
    } else {
      node.setOnMouseReleased(
          e -> {
            f.handle(e);
            ff.handle(e);
          });
    }
  }
}
