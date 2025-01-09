package org.visual.data.structure.graph;


import java.util.List;

public interface Node extends GraphicalObject {

  List<Connector> connectors();

  void addConnector(Connector connector);
}
