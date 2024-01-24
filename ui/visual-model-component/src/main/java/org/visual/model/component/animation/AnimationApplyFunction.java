package org.visual.model.component.animation;

import org.visual.model.component.algebradata.AlgebraData;

@FunctionalInterface
public interface AnimationApplyFunction<T extends AlgebraData<T>> {
    void apply(AnimationNode<T> from, AnimationNode<T> to, T data);
}
