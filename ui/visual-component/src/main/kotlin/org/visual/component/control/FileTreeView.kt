package org.visual.component.control

import io.github.oshai.kotlinlogging.KotlinLogging
import it.unimi.dsi.fastutil.objects.Object2IntSortedMaps
import java.nio.file.*
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import kotlin.io.path.isDirectory
import lombok.extern.slf4j.Slf4j

@Slf4j
class FileTreeView : TreeView<Path>() {
  private val log = KotlinLogging.logger {}
  private val _fileRoot = SimpleObjectProperty<Path>()
  private val watcher: WatchService = FileSystems.getDefault().newWatchService()
  private val threadFactory = Thread.ofVirtual().name(this::class.simpleName, 0)
  private val activeCount by lazy { Object2IntSortedMaps.emptyMap<String>() }

  var fileRoot: Path?
    get() = _fileRoot.get()
    set(value) {
      require(!(value != null && !value.isDirectory())) { "Root must be a directory" }
      _fileRoot.set(value)
    }

  init {
    isShowRoot = false
    _fileRoot.addListener { _: ObservableValue<out Path?>?, _: Path?, _: Path? -> renderTree() }
  }

  private fun renderTree() {
    val rootItem = TreeItem(_fileRoot.get())
    rootItem.isExpanded = true
    createTreeItems(rootItem, _fileRoot.get())
    root = rootItem
  }

  private fun createTreeItems(parentItem: TreeItem<Path?>, directory: Path?) {
    val path = _fileRoot.get()
    path.register(
        watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE)
    val files = directory
    if (files != null) {
      //      for (file in files) {
      //        val treeItem = TreeItem(file)
      //        parentItem.children.add(treeItem)
      //        if (file.isDirectory) {
      //          try {
      //            file.toPath()
      //              .register(
      //                watcher,
      //                StandardWatchEventKinds.ENTRY_CREATE,
      //                StandardWatchEventKinds.ENTRY_DELETE
      //              )
      //          } catch (e: IOException) {
      //            e.printStackTrace()
      //          }
      //          createTreeItems(treeItem, file)
      //        }
      //      }
    }
  }

  private fun listen() {
    threadFactory.start {
      while (true) {
        var key: WatchKey
        try {
          key = watcher.take()
          for (event in key.pollEvents()) {
            log.atInfo { message = "e:" + event.kind().type() }
            log.atInfo { message = "e:" + event.context() }
            val context = event.context() as Path
            Platform.runLater { this.renderTree() }
          }
          key.reset()
        } catch (e: InterruptedException) {
          e.printStackTrace()
        }
      }
    }
  }
}
