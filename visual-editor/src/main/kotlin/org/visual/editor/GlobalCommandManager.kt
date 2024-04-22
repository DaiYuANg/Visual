package org.visual.editor

import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

class GlobalCommandManager : CommandManager {
  private val undoStack: Deque<Command> = ConcurrentLinkedDeque()
  private val redoStack: Deque<Command> = ConcurrentLinkedDeque()

  override fun executeCommand(cmd: Command) {
    cmd.execute()
    undoStack.push(cmd)
    redoStack.clear()
  }

  override fun undo() {
    undoStack.isNotEmpty().apply {
      val command = undoStack.pop()
      command.undo()
      redoStack.push(command)
    }
  }

  override fun redo() {
    redoStack.isNotEmpty().apply {
      val command = redoStack.pop()
      command.execute()
      undoStack.push(command)
    }
  }
}
