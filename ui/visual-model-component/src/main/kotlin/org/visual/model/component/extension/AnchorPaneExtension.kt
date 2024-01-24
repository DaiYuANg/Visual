package org.visual.model.component.extension

import javafx.scene.Node
import javafx.scene.layout.AnchorPane.*

fun setTopLeftAnchor(child: Node) {
  setTopAnchor(child, 0.0)
  setLeftAnchor(child, 0.0)
}

fun setTopRightAnchor(child: Node) {
  setTopAnchor(child, 0.0)
  setRightAnchor(child, 0.0)
}

fun setBottomRightAnchor(child: Node) {
  setBottomAnchor(child, 0.0)
  setRightAnchor(child, 0.0)
}

fun setBottomLeftAnchor(child: Node) {
  setBottomAnchor(child, 0.0)
  setLeftAnchor(child, 0.0)
}
