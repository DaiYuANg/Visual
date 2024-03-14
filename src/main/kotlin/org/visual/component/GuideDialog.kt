package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.nio.file.Path
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane

@Singleton
class GuideDialog @Inject constructor(guideContent: GuideContent) : Dialog<Path>() {

  private val log = KotlinLogging.logger {}

  private val finishButton = ButtonType.FINISH

  private val cancelButton = ButtonType.CANCEL

  private val result = SimpleObjectProperty<Path>()

  private val guidePane by lazy {
    DialogPane().apply {
      buttonTypes.addAll(finishButton, cancelButton)
      content = guideContent
    }
  }

  init {
    isResizable = true
    dialogPane = guidePane
  }

  init {
    guideContent.listenChoose { result.set(it) }
  }

  init {
    setResultConverter {
      log.atTrace { message = "buttonType:${it}" }
      if (it.buttonData == ButtonBar.ButtonData.FINISH) {
        result.get()
      }
      result.get()
    }
  }
}
