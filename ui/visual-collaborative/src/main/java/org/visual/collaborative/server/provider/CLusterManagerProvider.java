package org.visual.collaborative.server.provider;

import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CLusterManagerProvider implements Provider<ClusterManager> {

  @Override
  public ClusterManager get() {
    return new HazelcastClusterManager();
  }
}
