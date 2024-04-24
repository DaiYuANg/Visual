package org.visual.dsl

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.VBox

class VBoxBuilder {
  var spacing: Double? = null
  var padding: Insets? = null
  private val children = mutableListOf<Node>()

  fun build(): VBox {
    val vbox = VBox()
    spacing?.let { vbox.spacing = it }
    padding?.let { vbox.padding = it }
    children.forEach(vbox.children::add)
    return vbox
  }

  fun addChild(node: Node) {
    children.add(node)
  }
}
