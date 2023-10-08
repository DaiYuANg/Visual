package org.visual.model.di.providers;

import com.google.inject.Provider;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class VertxProvider implements Provider<Vertx> {
	@Override
	public Vertx get() {
		val vertx = Vertx.vertx();
		vertx.exceptionHandler(event -> {
            throw new RuntimeException(event);
        });
		return vertx;
	}
}
