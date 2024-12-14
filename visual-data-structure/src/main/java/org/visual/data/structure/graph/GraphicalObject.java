package org.visual.data.structure.graph;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

@Value.Immutable
@VisualDataStructureStyle
public interface GraphicalObject {
  String id();

  String type();

  int x();

  int y();

  Properties properties();
}
