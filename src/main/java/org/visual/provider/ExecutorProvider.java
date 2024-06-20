package org.visual.provider;

import io.avaje.inject.Lazy;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Singleton
@Lazy
public class ExecutorProvider implements Provider<Executor> {

  @Override
  public Executor get() {
    return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }
}
