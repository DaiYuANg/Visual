package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.Verticle;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.verticles.RootVerticle;

@Slf4j
public class VerticleModule extends AbstractModule {

    public VerticleModule(){
        log.atInfo().log("verticle module init");
    }

    @Override
    protected void configure() {
        val verticleMultibinder = Multibinder.newSetBinder(binder(), Verticle.class);
        verticleMultibinder.addBinding().to(RootVerticle.class);
    }
}
