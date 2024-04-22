package org.visual.editor

interface CommandManager {

  fun executeCommand(cmd: Command)

  fun undo()

  fun redo()
}
