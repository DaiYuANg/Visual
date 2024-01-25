package org.visual.model.component.display

import javafx.scene.control.MenuBar
import org.visual.model.component.annotation.FxComponent

@FxComponent
open class SystemMenuBar : MenuBar() {
  init {
    isUseSystemMenuBar = true
  }
}
