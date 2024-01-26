package org.visual.model.component.util

import javafx.scene.Node

fun <T : Node?> getContainer(node: Node, containerType: Class<T>): T? {
  val parent = node.parent ?: return null
  if (containerType.isInstance(parent)) {
    return containerType.cast(parent)
  }
  return getContainer(parent, containerType)
}
