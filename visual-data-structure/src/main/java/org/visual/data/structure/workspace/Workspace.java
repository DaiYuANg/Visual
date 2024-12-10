package org.visual.data.structure.workspace;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

@Value.Immutable
@VisualDataStructureStyle
public interface Workspace {
  String id();

  String name();

  String description();


}
