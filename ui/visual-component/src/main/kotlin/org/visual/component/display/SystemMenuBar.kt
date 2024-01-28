package org.visual.component.display

import javafx.scene.control.MenuBar
import org.visual.component.annotation.FxComponent

@FxComponent
open class SystemMenuBar : MenuBar() {
  init {
    isUseSystemMenuBar = true
  }
}
