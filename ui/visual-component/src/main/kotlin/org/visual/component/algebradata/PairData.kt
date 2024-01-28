package org.visual.component.algebradata

data class PairData<A : AlgebraData<A>, B : AlgebraData<B>>(val a: A, val b: B) :
    AlgebraData<PairData<A, B>> {
  override fun plus(other: PairData<A, B>): PairData<A, B> {
    return PairData(a.plus(other.a), b.plus(other.b))
  }

  override fun minus(other: PairData<A, B>): PairData<A, B> {
    return PairData(a.minus(other.a), b.minus(other.b))
  }

  override fun multiply(v: Double): PairData<A, B> {
    return PairData(a.multiply(v), b.multiply(v))
  }

  override fun dividedBy(v: Double): PairData<A, B> {
    return PairData(a.dividedBy(v), b.dividedBy(v))
  }
}
