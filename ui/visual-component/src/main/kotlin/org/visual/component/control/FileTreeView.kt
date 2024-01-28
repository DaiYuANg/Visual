package org.visual.component.control

import java.io.File
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import org.apache.commons.io.monitor.FileAlterationListener
import org.apache.commons.io.monitor.FileAlterationMonitor
import org.apache.commons.io.monitor.FileAlterationObserver

class FileTreeView : TreeView<File>() {

  private val _fileRoot = SimpleObjectProperty<File>()
  private var fileMonitor: FileAlterationMonitor? = null

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

  private fun startFileMonitoring(rootDirectory: File?) {
    rootDirectory?.let {
      try {
        fileMonitor?.stop()
        val observer = FileAlterationObserver(rootDirectory)
        observer.addListener(
            object : FileAlterationListener {
              override fun onFileCreate(file: File?) {
                // Handle file creation event
                println("File created: $file")
                renderTree() // Refresh the tree view
              }

              override fun onDirectoryChange(p0: File?) {
                println("File created: $p0")
              }

              override fun onDirectoryCreate(p0: File?) {
                println("File created: $p0")
              }

              override fun onDirectoryDelete(p0: File?) {
                println("File created: $p0")
              }

              override fun onFileChange(file: File?) {
                // Handle file modification event
                println("File modified: $file")
                renderTree() // Refresh the tree view
              }

              override fun onFileDelete(file: File?) {
                // Handle file deletion event
                println("File deleted: $file")
                renderTree() // Refresh the tree view
              }

              override fun onStart(p0: FileAlterationObserver?) {
                TODO("Not yet implemented")
              }

              override fun onStop(p0: FileAlterationObserver?) {
                TODO("Not yet implemented")
              }
            })

        fileMonitor = FileAlterationMonitor(1000) // Check every 1 second
        fileMonitor?.addObserver(observer)
        fileMonitor?.start()
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
}
