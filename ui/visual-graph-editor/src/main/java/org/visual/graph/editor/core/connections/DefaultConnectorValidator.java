/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.core.connections;

import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.GConnectorValidator;
import org.visual.graph.editor.core.connectors.DefaultConnectorTypes;
import org.visual.graph.editor.model.GConnector;

/** Default validation rules that determine which connectors can be connected to each other. */
public class DefaultConnectorValidator implements GConnectorValidator {

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
    if (!source.getConnections().isEmpty() || !target.getConnections().isEmpty()) {
      return false;
    }
    if (source.getParent().equals(target.getParent())) {
      return false;
    }

    final boolean sourceIsInput = DefaultConnectorTypes.isInput(source.getType());
    final boolean targetIsInput = DefaultConnectorTypes.isInput(target.getType());

    return sourceIsInput != targetIsInput;
  }

  @Override
  public String createConnectionType(final GConnector source, final GConnector target) {
    return null;
  }

  @Override
  public String createJointType(final GConnector source, final GConnector target) {
    return null;
  }
}
