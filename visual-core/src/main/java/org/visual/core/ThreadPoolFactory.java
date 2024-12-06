package org.visual.core;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.val;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Factory
public class ThreadPoolFactory {

  @Bean
  Executor executor() {
    val factory = Thread.ofVirtual().name("visual-virtual-", 0).factory();
    return Executors.newThreadPerTaskExecutor(factory);
  }
}
