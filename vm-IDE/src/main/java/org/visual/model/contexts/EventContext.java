package org.visual.model.contexts;

import com.google.common.eventbus.EventBus;
import lombok.Setter;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.visual.model.EventSubscriber;

public enum EventContext {
    EVENT;

    private Reflections reflections;

    private final EventBus eventBus = new EventBus();

    @Setter
    private EventSubscriber eventSubscriber;

    EventContext() {
        AsyncContext.ASYNC.run(()->{
            this.reflections = new Reflections(
                    new ConfigurationBuilder().forPackages("org.visual.model")
            );
        });
    }
}
