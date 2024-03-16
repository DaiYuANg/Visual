package org.visual.component

import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon

class DiagramTreeView : TreeView<String>() {

  private val _root =
      TreeItem<String>().apply {
        graphic = FontIcon(FontAwesomeSolid.PROJECT_DIAGRAM)
        graphic.autosize()
      }

  init {
    root = _root
  }

  init {
    setOnContextMenuRequested {
      if (isSelectedRoot()) {
        val cm = DiagramTreeViewRootContextMenu()
        cm.show(scene.window, it.screenX, it.screenY)
      }
    }
  }

  private fun isSelectedRoot(): Boolean {
    return selectionModel.selectedItem == _root
  }
}
