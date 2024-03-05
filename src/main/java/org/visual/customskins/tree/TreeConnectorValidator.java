/* (C)2005-2014*/
package org.visual.customskins.tree;

import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.GConnectorValidator;
import org.visual.graph.editor.model.GConnector;

/** Validation rules for how connectors can be connected for the 'tree-like' graph. */
public class TreeConnectorValidator implements GConnectorValidator {

  @Override
  public boolean prevalidate(final GConnector source, final GConnector target) {

    if (source == null || target == null) {
      return false;
    } else return !source.equals(target);
  }

  @Override
  public boolean validate(final @NotNull GConnector source, final GConnector target) {

    if (source.getType() == null || target.getType() == null) {
      return false;
    }
    if (source.getParent().equals(target.getParent())) {
      return false;
    }
    if (source.getType().equals(target.getType())) {
      return false;
    }
    if (source.getType().equals(TreeSkinConstants.TREE_INPUT_CONNECTOR)
        && !source.getConnections().isEmpty()) {
      return false;
    } else
      return !target.getType().equals(TreeSkinConstants.TREE_INPUT_CONNECTOR)
          || target.getConnections().isEmpty();
  }

  @Override
  public String createConnectionType(final GConnector source, final GConnector target) {
    return TreeSkinConstants.TREE_CONNECTION;
  }

  @Override
  public String createJointType(final GConnector source, final GConnector target) {
    return null;
  }
}
