package org.visual.component.algebradata;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.visual.component.util.ColorUtil;

public class ColorData implements AlgebraData<ColorData> {
  private Color color;
  private final float h;
  private final float s;
  private final float b;
  private final double alpha;

  public ColorData(Color color) {
    this.color = color;
    float[] hsb = ColorUtil.toHSB(color);
    this.h = hsb[0];
    this.s = hsb[1];
    this.b = hsb[2];
    this.alpha = color.getOpacity();
  }

  public ColorData(float h, float s, float b, double alpha) {
    this.h = h;
    this.s = s;
    this.b = b;
    this.alpha = alpha;
  }

  public ColorData(float[] hsb, double alpha) {
    this.h = hsb[0];
    this.s = hsb[1];
    this.b = hsb[2];
    this.alpha = alpha;
  }

  public Color getColor() {
    if (color == null) {
      double alpha = this.alpha;
      if (alpha > 1) {
        alpha = 1.0;
      } else if (alpha < 0) {
        alpha = 0.0;
      }
      color = ColorUtil.fromHSB(h, s, b, alpha);
    }
    return color;
  }

  @Override
  public ColorData plus(@NotNull ColorData other) {
    return new ColorData(h + other.h, s + other.s, b + other.b, alpha + other.alpha);
  }

  @Override
  public ColorData minus(@NotNull ColorData other) {
    return new ColorData(h - other.h, s - other.s, b - other.b, alpha - other.alpha);
  }

  @Override
  public ColorData multiply(double v) {
    return new ColorData((float) (h * v), (float) (s * v), (float) (b * v), alpha * v);
  }

  @Override
  public ColorData dividedBy(double v) {
    return new ColorData((float) (h / v), (float) (s / v), (float) (b / v), alpha / v);
  }
}
