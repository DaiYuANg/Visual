package org.visual.component.algebradata

import javafx.scene.paint.Color
import org.visual.component.util.ColorUtil.fromHSB
import org.visual.component.util.ColorUtil.toHSB

class ColorData : AlgebraData<ColorData> {
  private var color: Color? = null
  private val h: Float
  private val s: Float
  private val b: Float
  private val alpha: Double

  constructor(color: Color) {
    this.color = color
    val hsb = toHSB(color)
    h = hsb[0]
    s = hsb[1]
    b = hsb[2]
    alpha = color.opacity
  }

  constructor(h: Float, s: Float, b: Float, alpha: Double) {
    this.h = h
    this.s = s
    this.b = b
    this.alpha = alpha
  }

  constructor(hsb: FloatArray, alpha: Double) {
    h = hsb[0]
    s = hsb[1]
    b = hsb[2]
    this.alpha = alpha
  }

  fun getColor(): Color? {
    if (color == null) {
      var alpha = this.alpha
      when {
        alpha > 1 -> {
          alpha = 1.0
        }
        alpha < 0 -> {
          alpha = 0.0
        }
      }
      color = fromHSB(h, s, b, alpha)
    }
    return color
  }

  override fun plus(other: ColorData): ColorData {
    return ColorData(h + other.h, s + other.s, b + other.b, alpha + other.alpha)
  }

  override fun minus(other: ColorData): ColorData {
    return ColorData(h - other.h, s - other.s, b - other.b, alpha - other.alpha)
  }

  override fun multiply(v: Double): ColorData {
    return ColorData((h * v).toFloat(), (s * v).toFloat(), (b * v).toFloat(), alpha * v)
  }

  override fun dividedBy(v: Double): ColorData {
    return ColorData((h / v).toFloat(), (s / v).toFloat(), (b / v).toFloat(), alpha / v)
  }
}
