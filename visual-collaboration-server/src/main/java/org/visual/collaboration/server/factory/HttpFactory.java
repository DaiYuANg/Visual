package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.StaticHandler;
import lombok.val;
import org.visual.collaboration.server.model.HttpConfig;

@Factory
public class HttpFactory {

  @Bean
  Router router(Vertx vertx) {
    val router = Router.router(vertx);
    router.get().handler(StaticHandler.create());
    return router;
  }

  @Bean
  HttpServer httpServer(Vertx vertx, Router router) {
    return vertx.createHttpServer().requestHandler(router);
  }

  @Bean
  Uni<HttpServer> listen(HttpServer httpServer, HttpConfig httpConfig) {
    return httpServer.listen(httpConfig.port());
  }
}
