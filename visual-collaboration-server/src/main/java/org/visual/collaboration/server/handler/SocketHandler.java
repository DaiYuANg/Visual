package org.visual.collaboration.server.handler;

import io.vertx.mutiny.core.net.NetSocket;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class SocketHandler implements Consumer<NetSocket> {
  @Override
  public void accept(NetSocket netSocket) {

  }
}
