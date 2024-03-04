package org.visual.debugger.component

import javafx.scene.control.TreeCell
import javafx.scene.paint.Color
import org.visual.debugger.node.SVNode

object NodeTreeViewCell : TreeCell<SVNode>() {
  override fun updateItem(
      item: SVNode?,
      empty: Boolean,
  ) {
    super.updateItem(item, empty)
    val treeItem = treeItem
    graphic = treeItem?.graphic
    text = item?.toString()
    opacity = 1.0

    if (item == null) return
    if (!item.isVisible || item.isInvalidForFilter) {
      opacity = 0.3
    }

    if (item.isFocused) {
      textFill = Color.RED
    }
  }
}
