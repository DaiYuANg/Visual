package org.visual.data.structure.graph;

import java.util.List;

public interface Connector extends GraphicalObject {

  Node parent();

  List<Connection> connections();

  void addConnection(Connection connection);

  void removeConnection(Connection connection);

  Boolean isConnectionDetachedOnDrag();
}
