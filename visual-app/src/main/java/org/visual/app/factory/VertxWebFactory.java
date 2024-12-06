package org.visual.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.RequiredArgsConstructor;

@Factory
@RequiredArgsConstructor
public class VertxWebFactory {


  @Bean
  WebClient webClient(Vertx vertx) {
    return WebClient.create(vertx);
  }

  @Bean
  Router route(Vertx vertx) {
    return Router.router(vertx);
  }

  @Bean
  HttpServer httpServer(Vertx vertx) {
    return vertx.createHttpServer();
  }

}
