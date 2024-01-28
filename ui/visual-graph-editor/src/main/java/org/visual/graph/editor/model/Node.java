package org.visual.graph.editor.model;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Node implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  private long id;

  private double x;

  private double y;

  private double width;

  private double height;
}
