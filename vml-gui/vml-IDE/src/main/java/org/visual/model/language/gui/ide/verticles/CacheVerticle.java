package org.visual.model.language.gui.ide.verticles;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class CacheVerticle extends AbstractVerticle {
	private final String cacheRoot;

	@Inject
	public CacheVerticle(@Named("ApplicationCache") String cacheRoot) {
		this.cacheRoot = cacheRoot;
	}

	@Override
	public void init(Vertx vertx, Context context) {
	}
}
