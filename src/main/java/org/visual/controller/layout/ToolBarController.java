package org.visual.controller.layout;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.store.CanvasStore;

@Singleton
@Slf4j
public class ToolBarController implements Initializable {

  private final CanvasStore canvasStore = CanvasStore.INSTANCE;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void save(ActionEvent actionEvent) {}

  public void addItem(ActionEvent actionEvent) {
    val item = new Pane();
    item.setBackground(Background.fill(Color.ALICEBLUE));
    item.setPrefSize(100, 100);
    canvasStore.getListProperty().add(item);
  }
}
