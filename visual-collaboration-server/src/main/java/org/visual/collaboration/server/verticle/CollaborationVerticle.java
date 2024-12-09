package org.visual.collaboration.server.verticle;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.net.NetServer;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
@Named
public class CollaborationVerticle extends AbstractVerticle {
  private final NetServer netServer;

  @Override
  public Uni<Void> asyncStart() {
    return netServer.listen(19090).replaceWithVoid();
  }

  @Override
  public Uni<Void> asyncStop() {
    return netServer.close();
  }
}
