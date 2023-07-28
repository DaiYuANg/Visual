package org.visual.model.contexts;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum VertxContext {
	VERTX;

	private final Vertx vertx = Vertx.vertx();

	private final NetServer netServer = vertx.createNetServer().connectHandler(System.err::println);

	VertxContext() {
		TasksContext.ASYNC.run(() -> netServer.listen(8080));
	}
}
