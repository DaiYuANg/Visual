package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.StaticHandler;
import io.vertx.mutiny.ext.web.handler.TemplateHandler;
import io.vertx.mutiny.ext.web.templ.thymeleaf.ThymeleafTemplateEngine;
import lombok.val;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.visual.collaboration.server.model.HttpConfig;

@Factory
public class HttpFactory {

  @Bean
  ITemplateResolver classLoaderTemplateResolver() {
    val classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
    classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
    classLoaderTemplateResolver.setSuffix(".html");
    return classLoaderTemplateResolver;
  }

  @Bean
  TemplateHandler templateHandler(Vertx vertx, ITemplateResolver templateResolver) {
    val engine = ThymeleafTemplateEngine.create(vertx);
    engine.<TemplateEngine>unwrap().setTemplateResolver(templateResolver);
    return TemplateHandler.create(engine);
  }

  @Bean
  Router router(Vertx vertx, TemplateHandler templateHandler) {
    val router = Router.router(vertx);
    router.get().handler(StaticHandler.create());
    router.route("/assets/lib/*").handler(StaticHandler.create("META-INF/resources/webjars"));
    router.get("/*").handler(templateHandler);
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
