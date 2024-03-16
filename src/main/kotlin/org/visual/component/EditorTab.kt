package org.visual.component

import javafx.scene.control.Tab
import org.visual.constant.FXMLView
import org.visual.constant.NamingConstant
import org.visual.context.UIContext

class EditorTab @JvmOverloads constructor(name: String = NamingConstant.UNTITLED) : Tab() {
  private val tabContent = UIContext.INSTANCE.load(FXMLView.EDITOR)

  init {
    this.textProperty().set(name)
    content = tabContent
  }
}
