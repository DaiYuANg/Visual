package org.visual.component.util;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NodeUtils {
  public static <T extends Node> @Nullable T getContainer(
      @NotNull Node node, Class<T> containerType) {
    Node parent = node.getParent();
    if (parent == null) {
      return null;
    }

    if (containerType.isInstance(parent)) {
      return containerType.cast(parent);
    }

    return getContainer(parent, containerType);
  }
}
