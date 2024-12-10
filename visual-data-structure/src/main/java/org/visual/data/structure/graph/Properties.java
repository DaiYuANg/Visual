package org.visual.data.structure.graph;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

@Value.Immutable()
@VisualDataStructureStyle
public interface Properties {
  String color();

  Integer borderWidth();

  String borderColor();
}