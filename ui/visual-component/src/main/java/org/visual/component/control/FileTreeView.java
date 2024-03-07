package org.visual.component.control;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileTreeView extends TreeView<File> {
  private final SimpleObjectProperty<File> _fileRoot = new SimpleObjectProperty<>();
  private final WatchService watcher = FileSystems.getDefault().newWatchService();

  public File getFileRoot() {
    return _fileRoot.get();
  }

  public void setFileRoot(File value) {
    if (value != null && !value.isDirectory()) {
      throw new IllegalArgumentException("Root must be a directory");
    }
    _fileRoot.set(value);
  }

  public FileTreeView() throws IOException {
    setShowRoot(false);
    _fileRoot.addListener(
        (observable, oldValue, newValue) -> {
          renderTree();
        });
  }

  private void renderTree() {
    TreeItem<File> rootItem = new TreeItem<>(_fileRoot.get());
    rootItem.setExpanded(true);
    createTreeItems(rootItem, _fileRoot.get());
    setRoot(rootItem);
  }

  private void createTreeItems(TreeItem<File> parentItem, File directory) {
    Path path = _fileRoot.get().toPath();
    try {
      path.register(
          watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        TreeItem<File> treeItem = new TreeItem<>(file);
        parentItem.getChildren().add(treeItem);
        if (file.isDirectory()) {
          try {
            file.toPath()
                .register(
                    watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);
          } catch (IOException e) {
            e.printStackTrace();
          }
          createTreeItems(treeItem, file);
        }
      }
    }
  }

  private void listen() {
    CompletableFuture.runAsync(
        () -> {
          while (true) {
            WatchKey key;
            try {
              key = watcher.take();
              for (WatchEvent<?> event : key.pollEvents()) {
                log.info("e:" + event.kind().type());
                log.info("e:" + event.context());
                Path context = (Path) event.context();
                if (context != null) {
                  Platform.runLater(this::renderTree);
                }
              }
              key.reset();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });
  }
}
