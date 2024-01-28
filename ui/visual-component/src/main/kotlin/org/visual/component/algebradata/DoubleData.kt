package org.visual.component.algebradata

data class DoubleData(
    @JvmField val value: Double,
) : AlgebraData<DoubleData> {
  override fun multiply(v: Double): DoubleData {
    return DoubleData(value * v)
  }

  override fun dividedBy(v: Double): DoubleData {
    return DoubleData(value / v)
  }

  override fun toString(): String {
    return "DoubleData($value)"
  }

  override fun minus(other: DoubleData): DoubleData {
    return DoubleData(value - other.value)
  }

  override fun plus(other: DoubleData): DoubleData {
    return DoubleData(value + other.value)
  }
}
