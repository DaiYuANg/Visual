package org.visual.collaborative.provider;

import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClusterManagerProvider implements Provider<ClusterManager> {

  @Override
  public ClusterManager get() {
    return new HazelcastClusterManager();
  }
}
