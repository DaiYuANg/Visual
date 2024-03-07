package org.visual.collaborative.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.jetbrains.annotations.NotNull;

@Factory
public class VertxFactory {

  @Bean
  ClusterManager clusterManager() {
    return new HazelcastClusterManager();
  }

  @Bean
  Vertx vertx(ClusterManager clusterManager) {
    return Vertx.builder().withClusterManager(clusterManager).build();
  }

  @Bean
  NetServer netServer(@NotNull Vertx vertx) {
    return vertx.createNetServer();
  }
}
