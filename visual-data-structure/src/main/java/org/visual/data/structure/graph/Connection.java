package org.visual.data.structure.graph;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

import java.util.List;

@Value.Immutable
@VisualDataStructureStyle
public interface Connection extends Identifiable, Classifiable {
  Connector source();

  Connector target();

  List<Joint> joints();
}
