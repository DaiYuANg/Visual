package org.visual.component.display;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.visual.component.api.TitleBar;
import org.visual.component.util.ScreenUtil;
import org.visual.shared.singleton.OS;

public abstract class CommonTitleBar extends HBox implements TitleBar {
  private final SimpleDoubleProperty xOffset = new SimpleDoubleProperty(0.0);
  private final SimpleDoubleProperty yOffset = new SimpleDoubleProperty(0.0);

  private double prevWidth = 0.0;
  private double prevHeight = 0.0;

  private final Stage stage = (Stage) getScene().getWindow();

  public CommonTitleBar() {
    setPadding(new Insets(0.0, 0.0, 0.0, 0.0));
    setOnMouseDragged(
        e -> {
          getScene().getWindow().setX(e.getSceneX() - xOffset.get());
          getScene().getWindow().setY(e.getSceneY() - xOffset.get());
        });
    setOnMousePressed(
        e -> {
          getScene().getWindow().setOpacity(0.5);
          xOffset.set(e.getSceneX());
          yOffset.set(e.getSceneY());
        });
    setOnMouseReleased(e -> getScene().getWindow().setOpacity(1.0));
    addEventHandler(
        KeyEvent.KEY_TYPED,
        e -> {
          if (e.getCode().isFunctionKey() && e.getCode() == KeyCode.F11) {
            maximize(stage);
          }
        });
  }

  @Override
  public void close() {
    if (OS.OS == OS.MAC) {
      stage.setIconified(true);
      return;
    }
    stage.close();
  }

  @Override
  public void maximize() {
    maximize(stage);
  }

  @Override
  public void minimize() {
    stage.setIconified(true);
  }

  public void restoreSizeOrMax() {
    if (prevWidth == 0.0 && prevHeight == 0.0) {
      prevWidth = getScene().getWidth();
      prevHeight = getScene().getHeight();
      maximize(stage);
    } else {
      stage.setWidth(prevWidth);
      stage.setHeight(prevHeight);
    }
  }

  private void maximize(Stage stage) {
    stage.setX(ScreenUtil.primaryScreen.getMinX());
    stage.setY(ScreenUtil.primaryScreen.getMinY());
    stage.setWidth(ScreenUtil.primaryScreen.getWidth());
    stage.setHeight(ScreenUtil.primaryScreen.getHeight());
  }
}
