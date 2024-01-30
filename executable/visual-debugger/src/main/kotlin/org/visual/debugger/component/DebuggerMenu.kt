package org.visual.debugger.component

import javafx.scene.control.Menu
import javafx.scene.control.MenuItem

class DebuggerMenu : Menu() {

  private val openDebugger by lazy {
    MenuItem("Open Debugger")
  }

  init {
    text = "Debugger"
    items.addAll(openDebugger)
  }
}
