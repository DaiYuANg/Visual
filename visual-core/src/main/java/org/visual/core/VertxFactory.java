package org.visual.core;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxInternal;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxJmxMetricsOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import lombok.val;

import static io.smallrye.mutiny.infrastructure.Infrastructure.setOperatorLogger;

@Factory
public class VertxFactory {

    static {
        setOperatorLogger(new MutinySlf4jLogger());
    }

    @Bean
    Vertx mutinyVertx() {
        val option = new VertxOptions();
        val micrometer = new MicrometerMetricsOptions()
                .setJmxMetricsOptions(new VertxJmxMetricsOptions().setEnabled(true))
                .setEnabled(true);
        option.setMetricsOptions(micrometer);
        Vertx.vertx();
        return Vertx.builder()
                .with(option)
                .build();
    }

    @Bean
    Uni<Vertx> vertxUni(Vertx vertx) {
        val internal = (VertxInternal) vertx.getDelegate();
        Infrastructure.setDefaultExecutor(internal.getEventLoopGroup());
        return Uni.createFrom().item(vertx);
    }

    @Bean
    EventBus eventBus(Vertx vertx) {
        return vertx.eventBus();
    }
}
