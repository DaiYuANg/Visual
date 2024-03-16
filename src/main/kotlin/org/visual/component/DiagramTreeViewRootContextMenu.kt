package org.visual.component

import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import org.visual.context.CurrentContext
import org.visual.context.DiagramContext

class DiagramTreeViewRootContextMenu : ContextMenu() {

  private val newDiagramItem =
      MenuItem("New Diagram").apply {
        setOnAction { CurrentContext.current?.let { DiagramContext.newDiagram(it) } }
      }

  private val export = MenuItem("Export")

  init {
    items.addAll(newDiagramItem, export)
  }
}
