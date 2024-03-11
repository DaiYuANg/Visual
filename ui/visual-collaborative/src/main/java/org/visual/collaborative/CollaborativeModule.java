package org.visual.collaborative;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;
import io.vertx.core.spi.cluster.ClusterManager;
import lombok.extern.slf4j.Slf4j;
import org.visual.collaborative.provider.ClusterManagerProvider;
import org.visual.collaborative.provider.VertxProvider;

@Slf4j
public class CollaborativeModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ClusterManager.class).toProvider(ClusterManagerProvider.class);
    bind(Vertx.class).toProvider(VertxProvider.class);
  }
}
