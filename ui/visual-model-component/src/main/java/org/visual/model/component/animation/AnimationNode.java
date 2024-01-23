package org.visual.model.component.animation;


import org.visual.model.component.graph.GraphNode;
import org.visual.model.component.util.algebradata.AlgebraData;

public class AnimationNode<T extends AlgebraData<T>> extends GraphNode<AnimationNode<T>> {
    public final String name;
    public final T value;
    public final AnimationStateTransferFinishCallback<T> stateTransferFinish;

    public AnimationNode(String name, T value) {
        this(name, value, (from, to) -> {
        });
    }

    public AnimationNode(String name, T value,
                         AnimationStateTransferFinishCallback<T> stateTransferFinish) {
        super(name);
        this.name = name;
        this.value = value;
        this.stateTransferFinish = stateTransferFinish;
    }
}
