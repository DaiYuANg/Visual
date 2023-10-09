package org.visual.model.lifecycle.managers;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.DIContainer;
import org.visual.model.lifecycle.LifecycleManager;

import java.util.Map;

@Slf4j
public class VertxLifecycleManager implements LifecycleManager {

    private final Map<String, AbstractVerticle> verticles;

    private final Vertx vertx;

    private final TypeLiteral<Map<String, AbstractVerticle>> type = new TypeLiteral<>() {
    };

    public VertxLifecycleManager() {
        this.verticles = DIContainer.INSTANCE.getInjector().getInstance(Key.get(type));
        this.vertx = DIContainer.INSTANCE.getInjector().getInstance(Vertx.class);
    }

    @Override
    public void initialize() {
        log.atInfo().log("vertx initializer executing");
        val dp = new DeploymentOptions()
                .setHa(true)
                .setWorker(true);
        verticles.values().stream()
                .map(verticle -> vertx.deployVerticle(verticle, dp))
                .forEach(d -> {
                    d.onFailure(event -> log.error(event.getMessage(), event.fillInStackTrace()));
                    d.onComplete(event -> log.info(event.result()));
                });
    }

    @Override
    public void stop() {
        log.info("vertx lifecycle stop");
        vertx.deploymentIDs().forEach(vertx::undeploy);
    }
}
