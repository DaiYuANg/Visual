package org.visual.component.control

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds
import java.util.concurrent.CompletableFuture
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView

class FileTreeView : TreeView<File>() {

  private val _fileRoot by lazy { SimpleObjectProperty<File>() }

  private val watcher by lazy { FileSystems.getDefault().newWatchService() }

  private val log = KotlinLogging.logger {}

  var fileRoot: File?
    get() = _fileRoot.get()
    set(value) {
      require(value?.isDirectory == true) { "Root must be a directory" }
      _fileRoot.set(value)
    }

  init {
    isShowRoot = false
    _fileRoot.addListener { _, _, _ ->
      run {
        //        listen()
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
    val path = _fileRoot.get().toPath()
    path.register(
        watcher,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_DELETE,
    )
    directory.listFiles()?.forEach { file ->
      val treeItem = TreeItem(file)
      parentItem.children.add(treeItem)
      if (file.isDirectory) {
        file
            .toPath()
            .register(
                watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE)
        createTreeItems(treeItem, file)
      }
    }
  }

  private fun listen() {
    CompletableFuture.runAsync {
      while (true) {
        val key = watcher.take()
        key.pollEvents().forEach { event ->
          log.info {
            "e:${event.kind().type()}"
            "e:${event.context() as Path}"
          }
          val context = event.context() as? Path
          context?.let { Platform.runLater { renderTree() } }
        }
        key.reset()
      }
    }
  }
}
