package org.visual.model.initializing;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxSetup {
    @Getter
    private final Vertx vertx;

    @Getter
    private final EventBus eventBus;

    public VertxSetup() {
        this.vertx = Vertx.vertx(new VertxOptions());
        this.eventBus = vertx.eventBus();
    }
}
