/* (C)2024*/
package org.visual.model.app.handle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error(e.getMessage(), e);
    }
}
