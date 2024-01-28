package org.visual.component.animation;


import org.visual.component.algebradata.AlgebraData;

public interface AnimationStateTransferBeginCallback<T extends AlgebraData<T>> {
    void animationStateTransferBegin(AnimationNode<T> from, AnimationNode<T> to);
}
