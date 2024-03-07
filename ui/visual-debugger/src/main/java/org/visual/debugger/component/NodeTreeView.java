package org.visual.debugger.component;

import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import org.visual.debugger.node.SVNode;
import org.visual.debugger.view.NodeFilter;

public class NodeTreeView extends TreeView<SVNode> {

  private final Set<NodeFilter> nodeFilter;

  public NodeTreeView(Set<NodeFilter> nodeFilter) {
    this.nodeFilter = nodeFilter;
    setId("main-tree-view");
    setShowRoot(false);
    setCellFactory(param -> new NodeTreeViewCell());

    setOnMousePressed(
        ev -> {
          getContextMenu().hide();
          if (ev.isSecondaryButtonDown()) {
            // Handle secondary button press
          }
        });

    setOnMouseReleased(
        (EventHandler<MouseEvent>)
            ev -> {
              if (ev.isSecondaryButtonDown()) {
                // Handle secondary button release
              }
            });
  }
}
