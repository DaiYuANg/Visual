package org.visual.view;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.component.GlobalMenu;

@Lazy
@Slf4j
@Singleton
public class MainLayout extends VBox {

  public MainLayout(GlobalMenu globalMenu) {
    val content =
        new BorderPane() {
          {
            val button = new Button("test");
            setCenter(button);
          }
        };
    content.setTop(globalMenu);
    getChildren().add(content);
  }
}
