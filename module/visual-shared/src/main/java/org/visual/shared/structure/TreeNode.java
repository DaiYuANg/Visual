package org.visual.shared.structure;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;

@Getter
public class TreeNode<T> implements Serializable {
  private final T data;
  private final List<TreeNode<T>> children;

  public TreeNode(T data) {
    this.data = data;
    this.children = new ObjectArrayList<>();
  }

  public void addChild(TreeNode<T> child) {
    children.add(child);
  }

  public void removeChild(TreeNode<T> child) {
    children.remove(child);
  }
}
