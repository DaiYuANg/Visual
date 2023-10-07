package org.visual.model.initializing;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.DIContainer;

import java.util.Set;

@Slf4j
public class VertxInitializer implements Initializer {

    private final Set<Verticle> verticles;

    private final Vertx vertx;

    public VertxInitializer() {
        this.verticles = DIContainer.INSTANCE.getInjector().getInstance(Key.get(new TypeLiteral<Set<Verticle>>() {
        }));
        this.vertx = DIContainer.INSTANCE.getInjector().getInstance(Vertx.class);
    }

    @Override
    public void initialize() {
        log.atInfo().log("vertx initializer executing");
        verticles.forEach(vertx::deployVerticle);
    }
}
