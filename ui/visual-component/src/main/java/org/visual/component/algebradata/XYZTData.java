package org.visual.component.algebradata;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Data
public class XYZTData implements AlgebraData<XYZTData> {
  public final double x;
  public final double y;
  public final double z;
  public final double t;

  @Override
  public XYZTData plus(@NotNull XYZTData other) {
    return new XYZTData(x + other.x, y + other.y, z + other.z, t + other.t);
  }

  @Override
  public XYZTData minus(XYZTData other) {
    return new XYZTData(x - other.x, y - other.y, z - other.z, t - other.t);
  }

  @Override
  public XYZTData multiply(double v) {
    return new XYZTData(x * v, y * v, z * v, t * v);
  }

  @Override
  public XYZTData dividedBy(double v) {
    return new XYZTData(x / v, y / v, z / v, t / v);
  }
}
