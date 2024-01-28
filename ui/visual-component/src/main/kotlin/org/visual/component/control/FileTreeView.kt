package org.visual.component.control

import java.io.File
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView

class FileTreeView : TreeView<File>() {

  private val _fileRoot = SimpleObjectProperty<File>()

  var fileRoot: File?
    get() = _fileRoot.get()
    set(value) {
      require(value?.isDirectory == true) { "Root must be a directory" }
      _fileRoot.set(value)
    }

  init {
    _fileRoot.addListener { _, _, _ -> run { renderTree() } }
  }

  private fun renderTree() {
    val rootItem = TreeItem(_fileRoot.get())
    rootItem.isExpanded = true
    createTreeItems(rootItem, _fileRoot.get())
    this.root = rootItem
  }

  private fun createTreeItems(parentItem: TreeItem<File>, directory: File) {
    directory.listFiles()?.forEach { file ->
      val treeItem = TreeItem(file)
      parentItem.children.add(treeItem)

      if (file.isDirectory) {
        createTreeItems(treeItem, file)
      }
    }
  }
}
