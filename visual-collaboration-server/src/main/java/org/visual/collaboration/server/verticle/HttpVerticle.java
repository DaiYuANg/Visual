package org.visual.collaboration.server.verticle;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.http.HttpServer;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.collaboration.server.model.HttpConfig;

@Singleton
@Slf4j
@RequiredArgsConstructor
@Named
public class HttpVerticle extends AbstractVerticle {

  private final HttpServer httpServer;

  private final HttpConfig httpConfig;

  @Override
  public Uni<Void> asyncStart() {
    return httpServer.listen(httpConfig.port()).replaceWithVoid();
  }

  @Override
  public Uni<Void> asyncStop() {
    return httpServer.close();
  }
}
