package org.visual.model.app.module;

import com.google.inject.AbstractModule;
import org.visual.model.app.provider.ExecutorProvider;

import java.util.concurrent.Executor;

public class RootModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Executor.class).toProvider(ExecutorProvider.class);
    }
}
