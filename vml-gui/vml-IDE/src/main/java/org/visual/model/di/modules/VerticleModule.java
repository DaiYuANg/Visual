package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.verticles.*;

@Slf4j
public class VerticleModule extends AbstractModule {

    public VerticleModule(){
        log.atInfo().log("verticle module init");
    }

    @Override
    protected void configure() {
        val verticleMultiBinder = MapBinder.newMapBinder(binder(), String.class, AbstractVerticle.class);
        verticleMultiBinder.addBinding("root").to(RootVerticle.class).in(Singleton.class);
        verticleMultiBinder.addBinding("cache").to(CacheVerticle.class).in(Singleton.class);
        verticleMultiBinder.addBinding("workspace").to(WorkspaceVerticle.class).in(Singleton.class);
        verticleMultiBinder.addBinding("preference").to(PreferenceVerticle.class).in(Singleton.class);
        verticleMultiBinder.addBinding("i18n").to(I18nVerticle.class).in(Singleton.class);
    }
}
