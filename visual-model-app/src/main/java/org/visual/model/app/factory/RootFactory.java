/* (C)2024*/
package org.visual.model.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
public class RootFactory {

    @Bean
    Executor executor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
