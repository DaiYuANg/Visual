package org.visual.component.display

import javafx.collections.ListChangeListener
import javafx.scene.Node
import javafx.scene.layout.StackPane

open class SingleChildStackPane : StackPane() {

  init {
    children.addListener(
        ListChangeListener {
          if (it.list.size == 1) return@ListChangeListener
          if (it.list.size >= 2) {
            children.removeFirst()
          }
        })
  }

  fun setContent(node: Node) {
    super.getChildren().add(node)
  }
}
