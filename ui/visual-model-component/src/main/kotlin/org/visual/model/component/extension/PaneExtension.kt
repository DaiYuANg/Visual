package org.visual.model.component.extension

import javafx.scene.Cursor
import javafx.scene.layout.AnchorPane.setBottomAnchor
import javafx.scene.layout.AnchorPane.setLeftAnchor
import javafx.scene.layout.AnchorPane.setRightAnchor
import javafx.scene.layout.AnchorPane.setTopAnchor
import javafx.scene.layout.Pane

fun pane(init: Pane.() -> Unit): Pane {
  return Pane().apply(init)
}

fun Pane.layout(
    left: Double? = null,
    right: Double? = null,
    top: Double? = null,
    bottom: Double? = null
) {
  left?.let { setLeftAnchor(this, it) }
  right?.let { setRightAnchor(this, it) }
  top?.let { setTopAnchor(this, it) }
  bottom?.let { setBottomAnchor(this, it) }
}

fun Pane.cursor(cursor: Cursor) {
  this.cursor = cursor
}

fun Pane.prefSize(prefHeight: Double = 10.0, prefWidth: Double = 10.0) {
  this.prefHeight = prefHeight
  this.prefWidth = prefWidth
}
