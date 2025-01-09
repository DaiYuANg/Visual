package org.visual.data.structure.impl;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.visual.data.structure.graph.Connection;
import org.visual.data.structure.graph.Model;
import org.visual.data.structure.graph.Node;

import java.util.List;

@Data
@Slf4j
@Builder
public class VModel implements Model {

  private final MutableList<Node> nodes = Lists.mutable.empty();

  private final MutableList<Connection> connections = Lists.mutable.empty();

  private Double contentWidth;

  private Double contentHeight;

  @Override
  public List<Node> nodes() {
    return nodes.asUnmodifiable();
  }

  @Override
  public void addNode(Node node) {
    nodes.add(node);
  }

  @Override
  public void removeNode(Node node) {
    nodes.remove(node);
  }

  @Override
  public List<Connection> connections() {
    return connections.asUnmodifiable();
  }

  @Override
  public void addConnection(Connection connection) {
    connections.add(connection);
  }

  @Override
  public String type() {
    return "";
  }
}
