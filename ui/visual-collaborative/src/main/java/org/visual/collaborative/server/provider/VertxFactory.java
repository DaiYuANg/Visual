package org.visual.collaborative.server.provider;

import io.vertx.core.Vertx;
import io.vertx.core.spi.cluster.ClusterManager;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class VertxFactory implements Provider<Vertx> {

  private final ClusterManager clusterManager;

  @Override
  public Vertx get() {
    return Vertx.builder().withClusterManager(clusterManager).build();
  }
}
