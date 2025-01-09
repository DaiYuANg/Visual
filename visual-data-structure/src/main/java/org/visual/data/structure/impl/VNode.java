package org.visual.data.structure.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.visual.data.structure.graph.Connector;
import org.visual.data.structure.graph.Node;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@SuperBuilder
public class VNode extends AbstractGraphicalObject implements Node {

  private final String type;

  private final MutableList<Connector> connectors = Lists.mutable.empty();

  @Override
  public List<Connector> connectors() {
    return connectors.asUnmodifiable();
  }

  @Override
  public void addConnector(Connector connector) {
    connectors.add(connector);
  }

  @Override
  public String type() {
    return type;
  }
}
