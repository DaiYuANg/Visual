package org.visual.component.algebradata;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Data
public class XYData implements AlgebraData<XYData> {
  public final double x;
  public final double y;

  @Override
  public XYData plus(@NotNull XYData other) {
    return new XYData(x + other.x, y + other.y);
  }

  @Override
  public XYData minus(@NotNull XYData other) {
    return new XYData(x - other.x, y - other.y);
  }

  @Override
  public XYData multiply(double v) {
    return new XYData(x * v, y * v);
  }

  @Override
  public XYData dividedBy(double v) {
    return new XYData(x / v, y / v);
  }
}
