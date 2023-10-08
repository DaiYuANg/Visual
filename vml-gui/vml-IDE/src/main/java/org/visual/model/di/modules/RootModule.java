package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RootModule extends AbstractModule {
    @Override
    protected void configure() {
        log.atInfo().log("bind root modules");
        bindProviders();
    }

    private void bindServices() {
    }

    private void bindProviders() {

    }
}
