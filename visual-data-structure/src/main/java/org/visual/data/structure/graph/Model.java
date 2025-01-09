package org.visual.data.structure.graph;

import java.util.List;

public interface Model {
  List<Node> nodes();

  void addNode(Node node);

  void removeNode(Node node);

  List<Connection> connections();

  void addConnection(Connection connection);

  String type();

  Double getContentWidth();

  void setContentWidth(Double contentWidth);

  Double getContentHeight();

  void setContentHeight(Double contentHeight);
}
