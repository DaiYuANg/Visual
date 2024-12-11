package org.visual.app.component;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnlyOneStackPane extends StackPane {

  public void setContent(Node content) {
    getChildren().clear();
    getChildren().addFirst(content);
  }

  public Node getContent() {
    return getChildren().getFirst();
  }
}
