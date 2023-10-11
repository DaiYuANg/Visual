package org.visual.model.language.gui.ide.di.modules;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.di.providers.EventBusProvider;
import org.visual.model.language.gui.ide.di.providers.GsonProvider;
import org.visual.model.language.gui.ide.di.providers.VertxProvider;
import org.visual.model.language.gui.ide.di.providers.WorkspaceFileSystemListenerProvider;
import org.visual.model.language.gui.ide.functional.WorkspaceFileSystemListener;

@Slf4j
public class ProviderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Vertx.class).toProvider(VertxProvider.class).in(Singleton.class);
		bind(EventBus.class).toProvider(EventBusProvider.class).in(Singleton.class);
		bind(WorkspaceFileSystemListener.class).toProvider(WorkspaceFileSystemListenerProvider.class);
		bind(Gson.class).toProvider(GsonProvider.class);
	}
}
