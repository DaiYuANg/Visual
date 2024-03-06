package org.visual.component.algebradata;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Data
public class DoubleData implements AlgebraData<DoubleData> {
  public final double value;

  @Override
  public DoubleData multiply(double v) {
    return new DoubleData(value * v);
  }

  @Override
  public DoubleData dividedBy(double v) {
    return new DoubleData(value / v);
  }

  @Override
  public String toString() {
    return "DoubleData(" + value + ")";
  }

  @Override
  public DoubleData minus(@NotNull DoubleData other) {
    return new DoubleData(value - other.value);
  }

  @Override
  public DoubleData plus(@NotNull DoubleData other) {
    return new DoubleData(value + other.value);
  }
}
