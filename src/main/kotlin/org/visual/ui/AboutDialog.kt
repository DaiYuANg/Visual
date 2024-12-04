package org.visual.ui

import javafx.scene.control.Dialog

class AboutDialog : Dialog<Void>() {
  init {
    title = "About"
    headerText = "Application Information"
  }
}