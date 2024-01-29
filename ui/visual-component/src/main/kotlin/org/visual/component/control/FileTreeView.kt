package org.visual.component.control

import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import org.slf4j.LoggerFactory

class FileTreeView : TreeView<File>() {

  private val _fileRoot = SimpleObjectProperty<File>()

  private val log = LoggerFactory.getLogger(FileTreeView::class.java)

  var fileRoot: File?
    get() = _fileRoot.get()
    set(value) {
      require(value?.isDirectory == true) { "Root must be a directory" }
      _fileRoot.set(value)
    }

  init {
    _fileRoot.addListener { _, _, _ ->
      run {
        listen()
        renderTree()
      }
    }
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

  private fun listen() {
    val path = _fileRoot.get().toPath()
    val fileSystem = FileSystems.getDefault()
    val watchService = fileSystem.newWatchService()
    path.register(
        watchService,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_DELETE,
        StandardWatchEventKinds.ENTRY_MODIFY)
    Thread.ofVirtual().name(_fileRoot.get().absolutePath, 0).start {
      while (true) {
        val key = watchService.take()
        key.pollEvents().forEach { event ->
          log.info("e:{}", event.kind().type())
          log.info("e:{}", event.kind().name())
          val context = event.context() as? Path
          context?.let { renderTree() }
        }
        key.reset()
      }
    }
  }
}
