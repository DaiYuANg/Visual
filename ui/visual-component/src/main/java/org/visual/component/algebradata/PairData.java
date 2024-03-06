package org.visual.component.algebradata;

import org.jetbrains.annotations.NotNull;

public class PairData<A extends AlgebraData<A>, B extends AlgebraData<B>>
    implements AlgebraData<PairData<A, B>> {
  public final A a;
  public final B b;

  public PairData(A a, B b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public PairData<A, B> plus(@NotNull PairData<A, B> other) {
    return new PairData<>(a.plus(other.a), b.plus(other.b));
  }

  @Override
  public PairData<A, B> minus(@NotNull PairData<A, B> other) {
    return new PairData<>(a.minus(other.a), b.minus(other.b));
  }

  @Override
  public PairData<A, B> multiply(double v) {
    return new PairData<>(a.multiply(v), b.multiply(v));
  }

  @Override
  public PairData<A, B> dividedBy(double v) {
    return new PairData<>(a.dividedBy(v), b.dividedBy(v));
  }
}
