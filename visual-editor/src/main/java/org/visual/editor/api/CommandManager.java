package org.visual.editor.api;

import org.jetbrains.annotations.NotNull;

public interface CommandManager {
  void executeCommand(@NotNull Command command);

  void undo();

  void redo();
}
