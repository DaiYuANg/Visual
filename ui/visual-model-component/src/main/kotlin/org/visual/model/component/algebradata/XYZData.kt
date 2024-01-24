package org.visual.model.component.algebradata

data class XYZData(val x: Double, val y: Double, val z: Double) : AlgebraData<XYZData> {
  override fun plus(other: XYZData): XYZData {
    return XYZData(x + other.x, y + other.y, z + other.z)
  }

  override fun minus(other: XYZData): XYZData {
    return XYZData(x - other.x, y - other.y, z - other.z)
  }

  override fun multiply(v: Double): XYZData {
    return XYZData(x * v, y * v, z * v)
  }

  override fun dividedBy(v: Double): XYZData {
    return XYZData(x / v, y / v, z / v)
  }

  override fun toString(): String {
    return "XYZData($x, $y, $z)"
  }
}
