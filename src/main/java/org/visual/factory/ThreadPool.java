package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.val;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
public class ThreadPool {

  @Bean
  Executor executor() {
    val factory = Thread.ofVirtual().name("visual-virtual-", 0).factory();
    return Executors.newThreadPerTaskExecutor(factory);
  }
}
