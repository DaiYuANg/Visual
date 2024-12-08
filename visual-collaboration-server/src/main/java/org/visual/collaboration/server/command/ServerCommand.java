package org.visual.collaboration.server.command;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.net.NetServer;
import io.vertx.mutiny.ext.eventbus.bridge.tcp.TcpEventBusBridge;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;

@CommandLine.Command(name = "server", aliases = "s")
@Slf4j
@Singleton
@RequiredArgsConstructor
public class ServerCommand implements Runnable {

  private final NetServer socketServer;

  private final TcpEventBusBridge tcpEventBusBridge;


  @Override
  public void run() {
    log.atInfo().log("Starting server...");
    val startSocketServer = socketServer.listen(22222).log("Start socket server");
    val startTcpEventBusBridge = tcpEventBusBridge.listen(11111).log("Start Tcp Event Bridge");
    Uni.combine().all()
      .unis(startSocketServer, startTcpEventBusBridge)
      .usingConcurrencyOf(2)
      .discardItems()
      .subscribe()
      .with(t -> log.atInfo().log("Collaboration Server Startup"));
  }
}
