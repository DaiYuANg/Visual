package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.PostConstruct;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Route;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executor;

@Factory
@RequiredArgsConstructor
public class Net {
  private final Executor executor;

  @PostConstruct
  void integrateMutiny() {
    Infrastructure.setDefaultExecutor(executor);
  }

  @Bean
  Vertx mutinyVertx() {
    return Vertx.vertx();
  }

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
