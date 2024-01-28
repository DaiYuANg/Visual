package org.visual.event;

import java.util.Collection;

public interface EventPublisher {

  <E extends AbstractEvent> void addListener(ListenerArgument<E> argument);

  <E extends AbstractEvent> void addListener(Collection<ListenerArgument<E>> listenerArguments);

  <E extends AbstractEvent> void asyncPublish(E event);

  <E extends AbstractEvent> void publish(E event);
}
