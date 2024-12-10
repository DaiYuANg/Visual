package org.visual.data.structure.graph;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

import java.util.List;

@Value.Immutable
@VisualDataStructureStyle
public interface Rectangle extends GraphicalObject {
  List<GraphicalObject> children();

  GraphicalObject parent();
}
