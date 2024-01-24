package org.visual.model.component.algebradata

data class XYData(val x: Double, val y: Double) : AlgebraData<XYData> {
  override fun plus(other: XYData): XYData {
    return XYData(x + other.x, y + other.y)
  }

  override fun minus(other: XYData): XYData {
    return XYData(x - other.x, y - other.y)
  }

  override fun multiply(v: Double): XYData {
    return XYData(x * v, y * v)
  }

  override fun dividedBy(v: Double): XYData {
    return XYData(x / v, y / v)
  }

  override fun toString(): String {
    return "XYData($x, $y)"
  }
}
