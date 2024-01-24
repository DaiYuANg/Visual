package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.val;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Factory
public class RootFactory {
    @Bean
    ExecutorService executor() {
        val factory = Thread.ofVirtual().name("preview", 0).factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }

}
