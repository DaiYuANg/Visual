package org.visual.provider;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.inject.Provider;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.visual.shared.singleton.OS;

public class ExecutorProvider implements Provider<Executor> {
  @Override
  public Executor get() {
    return new ThreadPoolExecutor(
        OS.cpuCore,
        OS.cpuCore + 1,
        1,
        TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(200),
        new ThreadFactoryBuilder().setDaemon(true).setNameFormat("visual-model-%s").build(),
        new ThreadPoolExecutor.CallerRunsPolicy());
  }
}
