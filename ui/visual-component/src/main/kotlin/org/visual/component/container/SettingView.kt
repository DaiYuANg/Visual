package org.visual.component.container

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.scene.control.SplitPane

class SettingView : SplitPane() {
  private val log = KotlinLogging.logger {}

  init {
    prefHeight = Double.POSITIVE_INFINITY
  }
}
