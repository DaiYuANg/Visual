@file:JvmName("FXUtil")

package org.visual.component.util

import javafx.application.Platform

fun runOnFX(r: Runnable) {
  when {
    Platform.isFxApplicationThread() -> {
      r.run()
    }
    else -> {
      Platform.runLater(r)
    }
  }
}
