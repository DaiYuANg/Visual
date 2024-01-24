package org.visual.model.component.algebradata

data class XYZTData(
    @JvmField val x: Double,
    @JvmField val y: Double,
    @JvmField val z: Double,
    @JvmField val t: Double
) : AlgebraData<XYZTData> {
  override fun plus(other: XYZTData): XYZTData {
    return XYZTData(x + other.x, y + other.y, z + other.z, t + other.t)
  }

  override fun minus(other: XYZTData): XYZTData {
    return XYZTData(x - other.x, y - other.y, z - other.z, t - other.t)
  }

  override fun multiply(v: Double): XYZTData {
    return XYZTData(x * v, y * v, z * v, t * v)
  }

  override fun dividedBy(v: Double): XYZTData {
    return XYZTData(x / v, y / v, z / v, t / v)
  }
}
