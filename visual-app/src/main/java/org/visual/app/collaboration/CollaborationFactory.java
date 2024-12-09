package org.visual.app.collaboration;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.net.NetClient;

@Factory
public class CollaborationFactory {

  @Bean
  NetClient collaborationClient(Vertx vertx) {
    return vertx.createNetClient();
  }
}
