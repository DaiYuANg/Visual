package org.visual.data.structure.diagram;

import org.immutables.value.Value;
import org.visual.data.structure.graph.GraphicalObject;
import org.visual.data.structure.style.VisualDataStructureStyle;

import java.util.List;

@Value.Immutable
@VisualDataStructureStyle
public interface Diagram {

  String version();

  DiagramMetadata metadata();

  List<GraphicalObject> elements();
}
