package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.val;
import org.visual.app.component.DraggableStackPane;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
public class EditorController implements Initializable {

  @FXML
  private AnchorPane area;
  @FXML
  private ScrollPane root;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    val stackPane = new DraggableStackPane();
    val rectangle = new Rectangle(200, 200, Color.BLUE);
    stackPane.getChildren().add(rectangle);
//    area.getChildren().add(stackPane);
  }
}
