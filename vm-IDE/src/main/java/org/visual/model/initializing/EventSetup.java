package org.visual.model.initializing;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.visual.model.VisualModelApplication;
import org.visual.model.annotations.EventSubscriber;
import org.visual.model.contexts.AsyncContext;

@Slf4j
public enum EventSetup {
    EVENT_SETUP;

    EventSetup() {
        AsyncContext.ASYNC.run(()->{
            val eventSubscriber = new Reflections(
                    new ConfigurationBuilder()
                            .forPackages(VisualModelApplication.class.getPackageName()))
                    .getTypesAnnotatedWith(EventSubscriber.class);
        });
    }
}
