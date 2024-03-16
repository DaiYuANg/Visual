package org.visual.component

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class SearchPane : Stage() {

  private val root = StackPane().apply { children.add(Button("Search")) }

  private val internalScene = Scene(root)

  init {
    scene = internalScene
  }
}
