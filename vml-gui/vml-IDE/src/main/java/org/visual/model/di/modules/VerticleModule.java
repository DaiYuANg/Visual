package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.vertx.core.Verticle;
import lombok.val;
import org.visual.model.initializing.Initializer;
import org.visual.model.verticles.RootVerticle;

public class VerticleModule extends AbstractModule {
    @Override
    protected void configure() {
        val verticleMultibinder = Multibinder.newSetBinder(binder(), Verticle.class);
        verticleMultibinder.addBinding().to(RootVerticle.class);
    }
}
