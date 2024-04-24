package org.visual.exception;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
  @Override
  public void uncaughtException(Thread t, Throwable e) {
    log.error(e.getMessage(), e);
  }
}
