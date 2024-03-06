package org.visual.component.algebradata;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Data
public class XYZData implements AlgebraData<XYZData> {
  public final double x;
  public final double y;
  public final double z;

  @Override
  public XYZData plus(@NotNull XYZData other) {
    return new XYZData(x + other.x, y + other.y, z + other.z);
  }

  @Override
  public XYZData minus(@NotNull XYZData other) {
    return new XYZData(x - other.x, y - other.y, z - other.z);
  }

  @Override
  public XYZData multiply(double v) {
    return new XYZData(x * v, y * v, z * v);
  }

  @Override
  public XYZData dividedBy(double v) {
    return new XYZData(x / v, y / v, z / v);
  }

  @Override
  public String toString() {
    return "XYZData(" + x + ", " + y + ", " + z + ")";
  }
}
