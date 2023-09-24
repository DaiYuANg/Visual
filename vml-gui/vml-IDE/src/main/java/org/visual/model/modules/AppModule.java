package org.visual.model.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Singleton
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ITest.class).to(Test.class);
    }

    @Provides
    Vertx vertx(){
        return Vertx.vertx();
    }
}