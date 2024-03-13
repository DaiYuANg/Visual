package org.visual.component

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane

@Singleton
class GuideDialog @Inject constructor(guideContent: GuideContent) : Dialog<String>() {

  private val guidePane by lazy {
    DialogPane().apply {
      buttonTypes.addAll(ButtonType.FINISH, ButtonType.CANCEL)
      content = guideContent
    }
  }

  init {
    isResizable = true
    dialogPane = guidePane
  }
}
