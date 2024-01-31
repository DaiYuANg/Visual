package org.visual.debugger.component

import javafx.event.EventHandler
import javafx.scene.control.TreeView
import javafx.scene.input.MouseEvent
import org.visual.debugger.node.SVNode
import org.visual.debugger.view.NodeFilter

class NodeTreeView(
    private val nodeFilter: MutableSet<NodeFilter>,
) : TreeView<SVNode>() {
  init {
    id = "main-tree-view"
    isShowRoot = false
    setCellFactory { NodeTreeViewCell }
    onMousePressed = EventHandler { ev: MouseEvent ->
      contextMenu.hide()
      if (ev.isSecondaryButtonDown) {
      }
    }

    onMouseReleased = EventHandler { ev: MouseEvent ->
      if (ev.isSecondaryButtonDown) {
      }
    }
  }
}
