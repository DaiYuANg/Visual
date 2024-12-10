package org.visual.collaboration.server.handler;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.core.net.NetSocket;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.agrona.concurrent.IdGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class SocketHandler implements Consumer<NetSocket> {

  private final Map<Long, NetSocket> f = new Object2ObjectOpenHashMap<>();

  private final IdGenerator idGenerator;

  private final MessageHandler messageHandler;

  @Override
  public void accept(@NotNull NetSocket netSocket) {
    netSocket.handler(buffer -> messageHandler.accept(netSocket,buffer));
    Uni.createFrom().item(idGenerator.nextId())
      .log()
      .invoke(id -> f.put(id, netSocket))
      .map(id -> JsonObject.of("Id", id).toString())
      .flatMap(message -> netSocket.write(Buffer.buffer(message)))
      .subscribe().with(t -> log.atInfo().log("SendMessage"));
  }
}
