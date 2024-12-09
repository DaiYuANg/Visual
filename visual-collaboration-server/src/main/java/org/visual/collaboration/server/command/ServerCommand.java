package org.visual.collaboration.server.command;

import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaboration.server.model.HttpConfig;
import picocli.CommandLine;

import java.util.Set;

import static io.smallrye.mutiny.Uni.combine;

@CommandLine.Command(name = "server", aliases = "s")
@Slf4j
@Singleton
@RequiredArgsConstructor
public class ServerCommand implements Runnable {

  private final Vertx vertx;
  private final Set<AbstractVerticle> verticles;
  private final HttpConfig httpConfig;

  @Override
  public void run() {
    log.atInfo().log("Starting server...");
    val uniDeploy = verticles.stream().map(vertx::deployVerticle).toList();
    combine()
      .all()
      .unis(uniDeploy)
      .usingConcurrencyOf(uniDeploy.size()).discardItems()
      .subscribe()
      .with(t ->
        log.atInfo().log("Collaboration Server Startup:http://localhost:{}", httpConfig.port())
      );
  }
}
