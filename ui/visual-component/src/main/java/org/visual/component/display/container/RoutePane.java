package org.visual.component.display.container;

import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class RoutePane extends StackPane {
  private final ConcurrentHashMap<String, Integer> routers = new ConcurrentHashMap<>();
  private int index = 0;

  public void addRoute(String path, Pane pane) {
    int currentIndex = getChildren().isEmpty() ? 0 : -1;
    getChildren().add(currentIndex, pane);
    routers.put(path, currentIndex);
  }

  public void next() {
    // Add your implementation for the 'next' method if needed
  }
}
