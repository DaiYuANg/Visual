package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.component.AutoScrollingEventWindow;

@Singleton
@Slf4j
public class ProjectController implements Initializable {

  @FXML AutoScrollingEventWindow window;
  @FXML TabPane root;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val canvas = new StackPane();
    canvas.setPrefSize(1000.0, 1000.0);
    canvas.setStyle(
        """
       -fx-border-color: blue ;
          -fx-border-width: 5 ;
          -fx-border-style: segments(10, 15, 15, 15)  line-cap round ;
      """);
    window.setContent(canvas);
  }
}
