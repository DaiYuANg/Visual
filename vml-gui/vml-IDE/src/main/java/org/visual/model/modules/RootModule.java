package org.visual.model.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import org.visual.model.services.IOperationSystemService;
import org.visual.model.services.IPreferenceService;
import org.visual.model.services.OperationSystemService;
import org.visual.model.services.PreferenceService;

@Singleton
public class RootModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IOperationSystemService.class).to(OperationSystemService.class);
        bind(IPreferenceService.class).to(PreferenceService.class);
        bind(Vertx.class).toProvider(VertxProvider.class);
    }
}