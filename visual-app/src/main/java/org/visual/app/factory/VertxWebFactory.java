package org.visual.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.RequiredArgsConstructor;

@Factory
@RequiredArgsConstructor
public class VertxWebFactory {


  @Bean
  Uni<WebClient> webClient(Uni<Vertx> vertx) {
    return vertx.log().map(WebClient::create);
  }

  @Bean
  Uni<Router> route(Uni<Vertx> vertx) {
    return vertx.map(Router::router);
  }

  @Bean
  Uni<HttpServer> httpServer(Uni<Vertx> vertx) {
    return vertx.map(Vertx::createHttpServer);
  }
}
