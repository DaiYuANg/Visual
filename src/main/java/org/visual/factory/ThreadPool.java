package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
public class ThreadPool {

  @Bean
  public Executor executor() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
}
