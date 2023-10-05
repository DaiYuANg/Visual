package org.visual.model.di.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusProvider implements Provider<EventBus> {

	@Inject
	Vertx vertx;

	@Override
	public EventBus get() {
		return vertx.eventBus();
	}
}
