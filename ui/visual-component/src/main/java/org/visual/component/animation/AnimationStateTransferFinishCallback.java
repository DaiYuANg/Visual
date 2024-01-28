package org.visual.component.animation;


import org.visual.component.algebradata.AlgebraData;

public interface AnimationStateTransferFinishCallback<T extends AlgebraData<T>> {
    void animationStateTransferFinish(AnimationNode<T> from, AnimationNode<T> to);
}
