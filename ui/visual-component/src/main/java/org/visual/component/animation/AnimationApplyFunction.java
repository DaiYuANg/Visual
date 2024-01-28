package org.visual.component.animation;

import org.visual.component.algebradata.AlgebraData;

@FunctionalInterface
public interface AnimationApplyFunction<T extends AlgebraData<T>> {
    void apply(AnimationNode<T> from, AnimationNode<T> to, T data);
}
