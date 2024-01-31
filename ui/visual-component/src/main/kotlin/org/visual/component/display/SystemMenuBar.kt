package org.visual.component.display

import javafx.scene.control.MenuBar

open class SystemMenuBar : MenuBar() {
  init {
    isUseSystemMenuBar = true
  }
}
