package org.visual.model.lifecycle.managers;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.DIContainer;
import org.visual.model.lifecycle.LifecycleManager;

import java.util.Map;

@Slf4j
public class VertxLifecycleManager implements LifecycleManager {

    private final Map<String,AbstractVerticle> verticles;

    private final Vertx vertx;

    private final TypeLiteral<Map<String,AbstractVerticle>> type = new TypeLiteral<>() {
    };

    public VertxLifecycleManager() {
        this.verticles = DIContainer.INSTANCE.getInjector().getInstance(Key.get(type));
        this.vertx = DIContainer.INSTANCE.getInjector().getInstance(Vertx.class);
    }

    @Override
    public void initialize() {
        log.atInfo().log("vertx initializer executing");
        verticles.values().stream().map(vertx::deployVerticle).forEach(d->{
            if (d.failed()){
                System.err.println(d.result());
            }
        });
    }

    @Override
    public void stop() {
        log.info("vertx lifecycle stop");
        vertx.deploymentIDs().forEach(vertx::undeploy);
    }
}
