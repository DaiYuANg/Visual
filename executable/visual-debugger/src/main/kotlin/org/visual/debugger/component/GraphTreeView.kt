package org.visual.debugger.component

import it.unimi.dsi.fastutil.ints.IntArrayList
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.TreeCell
import javafx.scene.control.TreeView
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import org.visual.debugger.node.SVNode
import org.visual.debugger.view.NodeFilter

class GraphTreeView(
  private val nodeFilter: MutableSet<NodeFilter>,
) : TreeView<SVNode>() {
  private val cellFactory by lazy {
    object : TreeCell<SVNode>() {
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
  }

  private val forcedCollapsedItems: List<Int> = IntArrayList()
  private val forcedExpandedItems: List<Int> = IntArrayList()
  private val forcedCollapsedNodeClassItems: List<String> = ArrayList()
  private val forcedExpandedNodeClassItems: List<String> = ArrayList()

  private val contextMenu =
      ContextMenu().apply {
        val node = selectionModel.selectedItem
        val hash = node.value.hashCode()
        val nodeClass = node.value.nodeClass
        val last = node.children.isEmpty()
        val collapsed: Boolean = forcedCollapsedItems.contains(hash)
        val expanded: Boolean = forcedExpandedItems.contains(hash)
        val collapsedClass: Boolean = forcedCollapsedNodeClassItems.contains(nodeClass)
        val expandedClass: Boolean = forcedExpandedNodeClassItems.contains(nodeClass)
      }

  init {
    id = "main-tree-view"
    isShowRoot = false
    setCellFactory { cellFactory }
    onMousePressed = EventHandler { ev: MouseEvent ->
      contextMenu.hide()
      if (ev.isSecondaryButtonDown) {
        //                showContextMenu(ev)
      }
    }

    onMouseReleased = EventHandler { ev: MouseEvent ->
      if (ev.isSecondaryButtonDown) {
        //                showContextMenu(ev)
      }
    }
  }
}
