package org.visual.collaboration.server.handler;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.core.net.NetSocket;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.data.structure.collaboration.VisualCommand;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class MessageHandler implements SocketMessageHandler {
  @Override
  public void accept(NetSocket netSocket, @NotNull Buffer buffer) {
    JsonObject message = new JsonObject(buffer.getDelegate());
    val command = Json.decodeValue(message.toString(), VisualCommand.class);
    log.atInfo().log("Command:{}", command);
  }
}
