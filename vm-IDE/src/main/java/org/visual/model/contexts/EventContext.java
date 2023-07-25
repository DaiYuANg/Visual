package org.visual.model.contexts;

import com.google.common.eventbus.EventBus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.visual.model.Scanner;
import org.visual.model.annotations.EventSubscriber;

@Slf4j
public enum EventContext {
    EVENT;

    private Reflections reflections;

    private final EventBus eventBus = new EventBus();

    @SneakyThrows
    EventContext(){
        val eventSubscriber = Scanner.INSTANCE.getScanner()
                .getTypesAnnotatedWith(EventSubscriber.class);
        val bus = new EventBus();
        for (Class<?> aClass : eventSubscriber) {
            bus.register(aClass.getDeclaredConstructor().newInstance());
        }
    }
}
