package org.visual.event;

public interface EventListener<E extends AbstractEvent> {

    void onAction(E event);
}
