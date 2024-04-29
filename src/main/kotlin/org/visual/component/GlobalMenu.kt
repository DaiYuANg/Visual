package org.visual.component

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar

@Singleton
class GlobalMenu @Inject constructor(fileMenuItem: FileMenuItem) : MenuBar() {

  private val menu = Menu("File").apply { items.add(fileMenuItem) }

  init {
    isUseSystemMenuBar = true
  }

  init {
    menus.add(menu)
  }
}
