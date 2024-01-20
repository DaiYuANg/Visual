package org.visual.model.shared.event;

import java.util.Collection;

public interface EventPublisher {

    <E extends AbstractEvent> void addListener(EventListener<E> listener);

    <E extends AbstractEvent> void addListener(Collection<EventListener<E>> listener);

    <E extends AbstractEvent> void asyncPublish(E event);

    <E extends AbstractEvent> void publish(E event);
}
