package org.visual.model.language.gui.ide.verticles;

import com.google.inject.Singleton;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class RootVerticle extends AbstractVerticle {

	@Override
	public void init(Vertx vertx, Context context) {
		log.atInfo().log("root verticle init");
	}

	@Override
	public void start(Promise<Void> startPromise) {
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
