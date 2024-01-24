package org.visual.model.component.algebradata

interface AlgebraData<T : AlgebraData<T>> {
  fun plus(other: T): T

  fun minus(other: T): T

  fun multiply(v: Double): T

  fun dividedBy(v: Double): T
}
