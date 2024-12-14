package org.visual.app.util;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.visual.data.structure.diagram.VisualDiagram;
import org.visual.data.structure.diagram.VisualDiagramMetadata;
import org.visual.data.structure.graph.VisualGraphicalObject;
import org.visual.data.structure.graph.VisualProperties;

import java.util.Date;
import java.util.UUID;

@UtilityClass
public class CreateDiagram {

  public VisualDiagram createDefaultDiagram() {
    val meta = VisualDiagramMetadata.builder().createAt(new Date())
      .lastModifiedAt(new Date())
      .title("untitled")
      .build();
    val prop = VisualProperties
      .builder()
      .color("red")
      .borderWidth(1)
      .borderColor("non")
      .width(20.0)
      .height(20.0)
      .build();
    VisualGraphicalObject.builder().id(UUID.randomUUID().toString()).type("rec").x(20).y(20).properties(prop).build();
    return VisualDiagram.builder().version("0.1")
      .metadata(meta)
      .build();
  }
}
