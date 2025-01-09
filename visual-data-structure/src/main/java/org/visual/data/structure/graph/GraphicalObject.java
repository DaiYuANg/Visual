package org.visual.data.structure.graph;

import lombok.NonNull;

public interface GraphicalObject extends Identifiable, Classifiable {
  Double getX();

  void setX(@NonNull Double x);

  Double getY();

  void setY(@NonNull Double y);

  Double getWidth();

  void setWidth(@NonNull Double width);

  Double getHeight();

  void setHeight(@NonNull Double height);
}
