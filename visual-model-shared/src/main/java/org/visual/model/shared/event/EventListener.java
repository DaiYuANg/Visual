package org.visual.model.shared.event;

public interface EventListener<E extends AbstractEvent> {

    void listen(E event);

    Class<E> type();
}
