package org.visual.data.structure.graph;

import org.jgrapht.Graph;
import org.visual.data.structure.style.VisualDataStructureStyle;

@VisualDataStructureStyle
public interface Workspace {
  Graph<String, String> graph();
}
