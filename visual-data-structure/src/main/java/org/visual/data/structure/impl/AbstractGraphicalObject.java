package org.visual.data.structure.impl;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.visual.data.structure.graph.GraphicalObject;

@Data
@SuperBuilder
public abstract class AbstractGraphicalObject implements GraphicalObject {
  private Double x;
  private Double y;
  private Double width;
  private Double height;
}
