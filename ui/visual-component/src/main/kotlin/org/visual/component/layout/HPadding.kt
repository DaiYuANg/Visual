package org.visual.component.layout

import javafx.geometry.Insets
import javafx.scene.layout.Pane

class HPadding(padding: Double) : Pane() {
  init {
    isVisible = false
    width = 0.0
    prefWidth = 0.0
    maxWidth = 0.0
    setPadding(Insets(0.0, 0.0, 0.0, padding))
  }
}
