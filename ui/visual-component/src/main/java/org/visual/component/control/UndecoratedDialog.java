package org.visual.component.control;

import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class UndecoratedDialog<R> extends Dialog<R> {
  private double xOffset = 0.0;
  private double yOffset = 0.0;

  public UndecoratedDialog() {
    initStyle(StageStyle.TRANSPARENT);
    setResizable(true);

    dialogPaneProperty()
        .addListener(
            (observable, oldValue, dialogPane) -> {
              dialogPane.setOnMousePressed(this::onMousePressed);
              dialogPane.setOnMouseDragged(this::onMouseDragged);
              dialogPane.setOnMouseReleased(this::onMouseReleased);
              dialogPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
            });
  }

  private void onMousePressed(MouseEvent event) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
  }

  private void onMouseDragged(MouseEvent event) {
    setX(event.getScreenX() - xOffset);
    setY(event.getScreenY() - yOffset);
  }

  private void onMouseReleased(MouseEvent event) {
    getDialogPane().setOpacity(1.0);
  }
}
