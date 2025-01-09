package org.visual.editor.api;

public interface Command {
  default boolean canExecute(){
    return true;
  }
  void execute();
  void undo();
}
