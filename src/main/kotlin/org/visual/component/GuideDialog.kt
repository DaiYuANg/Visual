package org.visual.component

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.control.Dialog

@Singleton
class GuideDialog
@Inject
constructor(
    guidePane: GuidePane,
) : Dialog<String?>() {

  init {
    guidePane.init()
    isResizable = true
    dialogPane = guidePane
  }
}
