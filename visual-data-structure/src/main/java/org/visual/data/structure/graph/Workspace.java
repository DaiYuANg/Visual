package org.visual.data.structure.graph;

import org.immutables.value.Value;
import org.jgrapht.Graph;
import org.visual.data.structure.style.VisualDataStructureStyle;

@Value.Immutable
@VisualDataStructureStyle
public interface Workspace {
  Graph<String, String> graph();
}
