package org.visual.debugger.context

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.SplitPane
import javafx.stage.Stage
import org.visual.shared.KSlf4j

@KSlf4j
object AttachSceneContext {

  val stage by lazy { SimpleObjectProperty<Stage>() }

  val nodeCounts by lazy { SimpleIntegerProperty(0) }

  init {
    stage.addListener { _, _, newValue -> run { nodeCounts.set(countNodes(newValue.scene.root)) } }
  }

  private fun countNodes(node: Node): Int {
    var count = 1

    if (node is Parent) {
      for (child in node.childrenUnmodifiable) {
        count += countNodes(child) // 递归计算子节点
      }
    }

    if (node is SplitPane) {
      System.err.println(node.items)
      for (child in node.items) {
        count += countNodes(child)
      }
    }

    return count
  }
}
