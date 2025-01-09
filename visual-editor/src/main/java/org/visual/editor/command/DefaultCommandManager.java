package org.visual.editor.command;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Stacks;
import org.eclipse.collections.api.stack.MutableStack;
import org.jetbrains.annotations.NotNull;
import org.visual.editor.api.Command;
import org.visual.editor.api.CommandManager;

@Slf4j
public class DefaultCommandManager implements CommandManager {
  private final MutableStack<Command> undoStack = Stacks.mutable.empty();
  private final MutableStack<Command> redoStack = Stacks.mutable.empty();

  @Override
  public void executeCommand(@NotNull Command command) {
    command.execute();
    undoStack.push(command);
    redoStack.clear();
  }

  @Override
  public void undo() {
    if (undoStack.isEmpty()) {
      return;
    }
    Command command = undoStack.pop();
    command.undo();
    redoStack.push(command);
  }

  @Override
  public void redo() {
    if (redoStack.isEmpty()) {
      return;
    }
    Command command = redoStack.pop();
    command.execute();
    undoStack.push(command);
  }
}
