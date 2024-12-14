package org.visual.data.structure.diagram;

import org.immutables.value.Value;
import org.visual.data.structure.style.VisualDataStructureStyle;

import java.util.Date;
import java.util.Map;

@Value.Immutable
@VisualDataStructureStyle
public interface DiagramMetadata {

  Date createAt();

  Date lastModifiedAt();

  String title();

  Map<String, Object> extra();
}
