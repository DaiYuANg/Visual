package org.visual.app.task;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.util.DialogPool;

import java.util.concurrent.TimeUnit;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class PrintDialogTask implements ScheduleTask {

  private final DialogPool pool;

  @Override
  public Long delay() {
    return 90000L;
  }

  @Override
  public TimeUnit timeUnit() {
    return TimeUnit.SECONDS;
  }

  @Override
  public void accept(Long aLong) {
    log.atInfo().log("Active dialog:{}", pool.getNumActive());
  }
}
