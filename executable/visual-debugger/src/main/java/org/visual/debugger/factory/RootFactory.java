package org.visual.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.val;

@Factory
public class RootFactory {
  @Bean
  ExecutorService executor() {
    val factory = Thread.ofVirtual().name("preview", 0).factory();
    return Executors.newThreadPerTaskExecutor(factory);
  }
}
