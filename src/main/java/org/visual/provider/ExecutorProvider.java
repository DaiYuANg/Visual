package org.visual.provider;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Singleton
public class ExecutorProvider implements Provider<Executor> {

  @Override
  public Executor get() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
}
