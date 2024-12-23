package org.visual.app.component;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class VisualScene extends Scene {
  public VisualScene(Parent root) {
    super(root);
    setOnMousePressed(event -> {
      log.atInfo().log("Mouse");
    });
    onMouseDragEnteredProperty().set(this::onDragged);
  }

  public void onDragged(MouseDragEvent event) {
    Optional.ofNullable(getWindow()).ifPresent(window -> {
      window.setOpacity(0.5);
    });
  }
}
