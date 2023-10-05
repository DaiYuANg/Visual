package org.visual.model.di.providers;

import com.google.inject.Provider;
import io.vertx.core.Vertx;

public class VertxProvider implements Provider<Vertx> {
	@Override
	public Vertx get() {
		return Vertx.vertx();
	}
}
