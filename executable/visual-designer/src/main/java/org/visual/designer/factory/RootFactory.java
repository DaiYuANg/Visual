/* (C)2024*/
package org.visual.designer.factory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.visual.shared.OS;

@Factory
@Slf4j
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
