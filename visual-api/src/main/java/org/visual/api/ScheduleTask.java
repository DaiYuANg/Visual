package org.visual.api;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public interface ScheduleTask extends Consumer<Long> {

  Long delay();

  TimeUnit timeUnit();

  default Long initialDelay() {
    return null;
  }
}
