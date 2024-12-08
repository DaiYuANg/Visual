package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.eventbus.bridge.tcp.TcpEventBusBridge;

@Factory
public class TcpEventBridgeFactory {
  @Bean
  TcpEventBusBridge tcpEventBridge(Vertx vertx) {
    return TcpEventBusBridge.create(vertx, new BridgeOptions()
      .addInboundPermitted(new PermittedOptions().setAddress("in"))
      .addOutboundPermitted(new PermittedOptions().setAddress("out"))
    );
  }
}
