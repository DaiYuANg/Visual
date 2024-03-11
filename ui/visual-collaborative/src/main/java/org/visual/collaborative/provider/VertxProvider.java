package org.visual.collaborative.provider;

import io.vertx.core.Vertx;
import io.vertx.core.spi.cluster.ClusterManager;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class VertxProvider implements Provider<Vertx> {

  private final ClusterManager clusterManager;

  @SneakyThrows
  @Override
  public Vertx get() {
    return Vertx.builder()
        .withClusterManager(clusterManager)
        .buildClustered()
        .toCompletionStage()
        .toCompletableFuture()
        .get();
  }
}
