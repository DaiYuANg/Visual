package org.visual.model.shared.event;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class SimpleEventPublisher implements EventPublisher {

    private final Supplier<ConcurrentHashMap<Class<? extends AbstractEvent>, EventListener<? extends AbstractEvent>>> listenerConcurrentHashMap = ConcurrentHashMap::new;

    private ConcurrentHashMap<Class<? extends AbstractEvent>, EventListener<? extends AbstractEvent>> lazyGet() {
        return listenerConcurrentHashMap.get();
    }

    @Override
    public <E extends AbstractEvent> void addListener(EventListener<E> listener) {
        lazyGet().put(listener.type(), listener);
    }

    @Override
    public <E extends AbstractEvent> void addListener(@NotNull Collection<EventListener<E>> listener) {
        val listeners = listener.stream()
                .collect(Collectors.toUnmodifiableMap(EventListener::type, Function.identity()));
        lazyGet().putAll(listeners);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends AbstractEvent> void asyncPublish(@NotNull E event) {
        Optional.ofNullable(lazyGet().get(event.getClass())).ifPresent(listener -> {
            val l = (EventListener<E>) listener;
            l.listen(event);
        });
    }

    @Override
    public <E extends AbstractEvent> void publish(E event) {

    }
}
