package org.visual.provider;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
@Lazy
public class ExecutorFactory {

  @Bean
  public Executor executor() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
}
