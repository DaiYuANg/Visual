package org.visual.collaborative.server.internal;

import io.vertx.core.*;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaborative.server.context.CollaborativeContext;

@Slf4j
public class CollaborativeServer extends AbstractVerticle {

  private final NetServer server =
      CollaborativeContext.INSTANCE.getInjector().getInstance(NetServer.class);

  private final Vertx vertx = CollaborativeContext.INSTANCE.getInjector().getInstance(Vertx.class);

  @Override
  public void start() {
    val listen = Future.await(server.connectHandler(event -> {}).listen());
    log.atInfo().log("start at：{}", listen.actualPort());
  }

  public void startServer() {

    vertx.deployVerticle(
        this, new DeploymentOptions().setThreadingModel(ThreadingModel.VIRTUAL_THREAD));
  }
}
