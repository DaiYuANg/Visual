package org.visual.model.component.animation;


import org.visual.model.component.util.algebradata.AlgebraData;

public interface AnimationStateTransferBeginCallback<T extends AlgebraData<T>> {
    void animationStateTransferBegin(AnimationNode<T> from, AnimationNode<T> to);
}
