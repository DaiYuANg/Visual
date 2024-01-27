package org.visual.model.designer.component

import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import org.visual.model.component.display.SystemMenuBar

class GlobalSystemMenu : SystemMenuBar() {

  private val exitItem by lazy {
    MenuItem("Exit").apply {
      accelerator = KeyCombination.keyCombination("CTRL+Q")
      setOnAction {
        val stage = scene.window as Stage
        stage.close()
      }
    }
  }

  private val fileMenu by lazy { Menu("File").apply { items.add(exitItem) } }

  init {
    menus.add(fileMenu)
  }
}
