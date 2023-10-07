package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.di.providers.EventBusProvider;
import org.visual.model.di.providers.VertxProvider;
import org.visual.model.services.ICacheService;
import org.visual.model.services.IOperationSystemService;
import org.visual.model.services.IPreferenceService;
import org.visual.model.services.IWorkspaceService;
import org.visual.model.services.impl.CacheService;
import org.visual.model.services.impl.OperationSystemService;
import org.visual.model.services.impl.PreferenceService;
import org.visual.model.services.impl.WorkspaceService;

@Slf4j
public class RootModule extends AbstractModule {
    @Override
    protected void configure() {
        log.atInfo().log("bind root modules");
        bindProviders();
        bindServices();
    }

    private void bindServices() {
        bind(IOperationSystemService.class).to(OperationSystemService.class).in(Singleton.class);
        bind(IPreferenceService.class).to(PreferenceService.class).asEagerSingleton();
        bind(ICacheService.class).to(CacheService.class).asEagerSingleton();
        bind(IWorkspaceService.class).to(WorkspaceService.class).asEagerSingleton();
    }

    private void bindProviders() {
        bind(Vertx.class).toProvider(VertxProvider.class).in(Singleton.class);
        bind(EventBus.class).toProvider(EventBusProvider.class).in(Singleton.class);
    }
}
