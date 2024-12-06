package org.visual.core;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxJmxMetricsOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.val;

import static io.smallrye.mutiny.infrastructure.Infrastructure.setOperatorLogger;

@Factory
public class VertxFactory {

  static {
    setOperatorLogger(new MutinySlf4jLogger());
  }

  @Bean
  ClusterManager clusterManager() {
    com.hazelcast.config.Config hazelcastConfig = new com.hazelcast.config.Config();
    hazelcastConfig.setProperty("hazelcast.logging.type", "slf4j");
    return new HazelcastClusterManager(hazelcastConfig);
  }

  @Bean
  Vertx mutinyVertx(ClusterManager clusterManager) {
    val option = new VertxOptions();
    val micrometer = new MicrometerMetricsOptions()
      .setJmxMetricsOptions(new VertxJmxMetricsOptions().setEnabled(true))
      .setEnabled(true);
    option.setMetricsOptions(micrometer);
    return Vertx.builder()
      .with(option)
      .withClusterManager(clusterManager)
      .buildClusteredAndAwait();
  }

  @Bean
  EventBus eventBus(Vertx vertx) {
    return vertx.eventBus();
  }
}
