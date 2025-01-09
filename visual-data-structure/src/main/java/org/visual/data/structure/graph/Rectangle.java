package org.visual.data.structure.graph;

import java.util.List;

public interface Rectangle extends GraphicalObject {
  List<GraphicalObject> children();

  GraphicalObject parent();
}
