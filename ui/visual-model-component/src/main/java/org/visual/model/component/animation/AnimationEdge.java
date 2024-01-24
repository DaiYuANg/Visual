package org.visual.model.component.animation;


import org.visual.model.component.algebradata.AlgebraData;
import org.visual.model.component.api.DoubleDoubleFunction;
import org.visual.model.component.graph.GraphEdge;

public class AnimationEdge<T extends AlgebraData<T>> extends GraphEdge<AnimationNode<T>> {
    public final AnimationNode<T> from;
    public final AnimationNode<T> to;
    public final long durationMillis;
    public final DoubleDoubleFunction function;

    AnimationEdge(AnimationNode<T> from, AnimationNode<T> to, long durationMillis, DoubleDoubleFunction function) {
        super(from, to, durationMillis);
        this.from = from;
        this.to = to;
        this.durationMillis = durationMillis;
        this.function = function;
    }
}
