package org.visual.model.event;

public interface EventListener<E extends AbstractEvent> {

    void onAction(E event);
}
