package org.visual.app.controller;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Lazy
@Singleton
@Slf4j
@RequiredArgsConstructor
public class MainLayoutController {
  @FXML
  private VBox table1;

  @FXML
  private VBox table2;

  @FXML
  private Button newTableButton;

  // 创建新表格的事件处理
  @FXML
  public void handleNewTable(ActionEvent event) {
    log.atInfo().log(table1.toString());
//    VBox newTable = new VBox();
//    newTable.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
//    newTable.getChildren().add(new javafx.scene.control.Label("New Table"));
//    // 在主界面添加新表格
//    table1.getParent().getChildrenUnmodifiable().add(newTable);
  }

  public void test(ActionEvent actionEvent) {
    log.atInfo().log("Test");
  }

  private void showAlert(String message) {
    val alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Action");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
