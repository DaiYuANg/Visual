package org.visual.debugger.context;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum AttachSceneContext {
  INSTANCE;

  private static final Logger log = LoggerFactory.getLogger(AttachSceneContext.class);

  @Getter private static final SimpleObjectProperty<Stage> stage = new SimpleObjectProperty<>();
  @Getter private static final SimpleIntegerProperty nodeCounts = new SimpleIntegerProperty(0);

  static {
    stage.addListener(
        (observable, oldValue, newValue) -> {
          log.debug("Node count updated: {}", countNodes(newValue.getScene().getRoot()));
          nodeCounts.set(countNodes(newValue.getScene().getRoot()));
        });
  }

  private static int countNodes(Node node) {
    int count = 1;

    if (node instanceof Parent) {
      for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
        count += countNodes(child); // 递归计算子节点
      }
    }

    if (node instanceof SplitPane) {
      log.warn("SplitPane detected: {}", ((SplitPane) node).getItems());
      for (Node child : ((SplitPane) node).getItems()) {
        count += countNodes(child);
      }
    }

    return count;
  }
}
