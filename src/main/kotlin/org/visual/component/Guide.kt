package org.visual.component

import javafx.scene.control.ListView
import javafx.scene.control.SplitPane
import javafx.scene.layout.StackPane

class Guide : SplitPane() {

  private val leftList = ListView<String>()

  private val rightPane = StackPane()

  init {
    children.addAll(leftList)
  }
}
