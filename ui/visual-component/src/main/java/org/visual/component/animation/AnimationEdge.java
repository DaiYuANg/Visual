package org.visual.component.animation;


import org.visual.component.algebradata.AlgebraData;
import org.visual.component.api.DoubleDoubleFunction;
import org.visual.component.graph.GraphEdge;

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
