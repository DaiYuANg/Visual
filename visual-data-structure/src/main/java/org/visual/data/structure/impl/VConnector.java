package org.visual.data.structure.impl;

import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.visual.data.structure.graph.Connection;
import org.visual.data.structure.graph.Connector;
import org.visual.data.structure.graph.Node;

import java.util.List;

@Slf4j
@SuperBuilder
public class VConnector extends AbstractGraphicalObject implements Connector {

  private Node node;

  private final MutableList<Connection> connections = Lists.mutable.empty();

  @Override
  public Node parent() {
    return node;
  }

  @Override
  public List<Connection> connections() {
    return connections.asUnmodifiable();
  }

  @Override
  public void addConnection(Connection connection) {
    if (connections.contains(connection)) {
      return;
    }
    connections.add(connection);
  }

  @Override
  public void removeConnection(Connection connection) {
    if (!connections.contains(connection)) {
      return;
    }
    connections.remove(connection);
  }

  @Override
  public Boolean isConnectionDetachedOnDrag() {
    return null;
  }

  @Override
  public String type() {
    return "";
  }
}
