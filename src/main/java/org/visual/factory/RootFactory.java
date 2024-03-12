package org.visual.factory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.visual.shared.singleton.OS;

@Factory
@Lazy
public class RootFactory {

  @Bean
  Executor executor() {
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
