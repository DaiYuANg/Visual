package org.visual.app.component;

import javafx.scene.layout.StackPane;
import lombok.val;

public class DraggableStackPane extends StackPane {
  private final double[] dragAnchor = new double[2];

  public DraggableStackPane() {
    super();
    bindEvent();
  }

  private void bindEvent() {
    setOnMousePressed(event -> {
      dragAnchor[0] = event.getSceneX() - getLayoutX();
      dragAnchor[1] = event.getSceneY() - getLayoutY();
    });

    setOnMouseDragged(event -> {
      // 计算鼠标拖动的距离并更新 StackPane 的位置
      val newX = event.getSceneX() - dragAnchor[0];
      val newY = event.getSceneY() - dragAnchor[1];

      // 设置 StackPane 新的布局位置
      setLayoutX(newX);
      setLayoutY(newY);
    });
  }
}
