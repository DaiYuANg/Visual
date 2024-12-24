package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.component.DataContent;
import org.visual.app.component.DraggableStackPane;
import org.visual.app.component.EditorContainer;
import org.visual.app.model.DatabaseObjBuilder;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class EditorController implements Initializable {

  @FXML
  private EditorContainer container;
  @FXML
  private ScrollPane root;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    val stackPane = new DraggableStackPane();
    stackPane.setBackground(Background.fill(Color.ALICEBLUE));
    val dc = new DataContent(DatabaseObjBuilder.builder()
      .name("test")
      .type("test")
      .build());
    container.setOnMouseDragged(event -> {
      System.err.println(container.getWidth());
      System.err.println(container.getHeight());
    });
    stackPane.getChildren().add(dc);
    container.getChildren().add(stackPane);
  }
}
