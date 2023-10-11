package org.visual.model.language.gui.ide.di.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusProvider implements Provider<EventBus> {

	@Inject
	private Vertx vertx;

	@Override
	@Singleton
	public EventBus get() {
		return vertx.eventBus();
	}
}
