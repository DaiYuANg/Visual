package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import org.visual.model.Project
import org.visual.store.GuideStore

@Singleton
class GuideDialog @Inject constructor(guideContent: GuideContent) : Dialog<Project>() {

  private val log = KotlinLogging.logger {}

  private val finishButton = ButtonType.FINISH

  private val guidePane by lazy {
    DialogPane().apply {
      buttonTypes.addAll(finishButton)
      content = guideContent
    }
  }

  init {
    isResizable = true
    dialogPane = guidePane
  }

  init {
    setResultConverter {
      log.atTrace { message = "button type${it}" }
      when (it) {
        ButtonType.FINISH -> {
          val path = GuideStore.toJdkPath()
          val name = GuideStore.projectName.get()
          Project.builder().path(path).name(name).build()
        }
        else -> {
          null
        }
      }
    }
  }
}
