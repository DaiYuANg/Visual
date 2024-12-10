package org.visual.collaboration.server.handler;

import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.core.net.NetSocket;

import java.util.function.BiConsumer;

public interface SocketMessageHandler extends BiConsumer<NetSocket, Buffer> {
}
