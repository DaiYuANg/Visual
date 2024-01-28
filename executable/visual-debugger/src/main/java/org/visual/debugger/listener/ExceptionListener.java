package org.visual.debugger.listener;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
@Singleton
public class ExceptionListener implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NotNull Thread t, Throwable e) {
        log.error(t.getName(),e.getMessage(),e);
    }
}
