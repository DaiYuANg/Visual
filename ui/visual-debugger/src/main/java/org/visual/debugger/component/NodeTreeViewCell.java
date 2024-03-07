package org.visual.debugger.component;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import org.visual.debugger.node.SVNode;

public class NodeTreeViewCell extends TreeCell<SVNode> {

  @Override
  protected void updateItem(SVNode item, boolean empty) {
    super.updateItem(item, empty);

    TreeItem<SVNode> treeItem = getTreeItem();
    setGraphic(treeItem != null ? treeItem.getGraphic() : null);
    setText(item != null ? item.toString() : "");
    setOpacity(1.0);

    if (item == null) {
      return;
    }

    if (!item.isVisible() || item.isInvalidForFilter()) {
      setOpacity(0.3);
    }

    if (item.isFocused()) {
      setTextFill(Color.RED);
    }
  }
}
