package org.visual.editor

interface Command {
  fun execute()

  fun undo()
}
