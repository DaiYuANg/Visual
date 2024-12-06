package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class WorkspaceController implements Initializable {
  @FXML
  private Separator separator;
  @FXML
  private HBox leftPane;
  @FXML
  private Separator separatorRight;
  private double initialX;
  private double initialWidth; // 按下时左侧区域的宽度

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    separator.setCursor(Cursor.H_RESIZE);
    separator.setOnMousePressed(this::onMousePressed);
    separator.setOnMouseDragged(this::onMouseDragged);
    separatorRight.setCursor(Cursor.H_RESIZE);
  }

  // 处理鼠标按下事件
  private void onMousePressed(@NotNull MouseEvent event) {
    initialX = event.getSceneX(); // 记录鼠标按下时的位置
    initialWidth = leftPane.getWidth(); // 记录左侧区域的初始宽度
  }

  // 处理鼠标拖动事件
  private void onMouseDragged(@NotNull MouseEvent event) {
    if (leftPane.getWidth() > 400) {
      return;
    }
    val offsetX = event.getSceneX() - initialX;
    val newWidth = initialWidth + offsetX;

    // 限制最小和最大宽度
    if (newWidth > 50 && newWidth < 400) {
      leftPane.setPrefWidth(newWidth); // 设置新的宽度
    }
  }
}
