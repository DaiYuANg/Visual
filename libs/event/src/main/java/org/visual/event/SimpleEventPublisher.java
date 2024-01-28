package org.visual.event;

import com.google.common.collect.*;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class SimpleEventPublisher implements EventPublisher {

    private final SetMultimap<Class<? extends AbstractEvent>, EventListener<? extends AbstractEvent>> listeners;

    {
        listeners = Multimaps.synchronizedSetMultimap(HashMultimap.create());
    }

    @Override
    public <E extends AbstractEvent> void addListener(@NotNull ListenerArgument<E> argument) {
        listeners.put(argument.getEvent(), argument.getListener());
    }

    @Override
    public <E extends AbstractEvent> void addListener(@NotNull Collection<ListenerArgument<E>> listenerArguments) {
        listenerArguments.forEach(this::addListener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends AbstractEvent> void asyncPublish(@NotNull E event) {
        val eventListeners = listeners.get(event.getClass());
        if (eventListeners.isEmpty()) return;
        eventListeners.forEach(listener -> {
            val thread = Thread.ofVirtual().name(this.getClass().getName(), 0);
            thread.start(() -> ((EventListener<E>) listener).onAction(event));
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends AbstractEvent> void publish(@NotNull E event) {
        val eventListeners = listeners.get(event.getClass());
        if (eventListeners.isEmpty()) return;
        eventListeners.forEach(listener -> ((EventListener<E>) listener).onAction(event));
    }
}
