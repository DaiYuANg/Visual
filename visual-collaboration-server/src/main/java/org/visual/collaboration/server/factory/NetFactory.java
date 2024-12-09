package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.net.NetServerOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.net.NetServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaboration.server.handler.SocketHandler;

@Slf4j
@Factory
@RequiredArgsConstructor
public class NetFactory {

  private final SocketHandler socketHandler;

  @Bean
  NetServer netServer(Vertx vertx) {
    val option = new NetServerOptions().setRegisterWriteHandler(true);
    return vertx.createNetServer(option).connectHandler(socketHandler);
  }
}
