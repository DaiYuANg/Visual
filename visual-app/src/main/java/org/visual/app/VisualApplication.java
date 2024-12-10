/* (C)2024*/
package org.visual.app;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.command.VisualCommand;
import org.visual.app.context.DIContext;
import org.visual.app.exception.GlobalExceptionHandler;

import static java.lang.Thread.setDefaultUncaughtExceptionHandler;

@Slf4j
public class VisualApplication {
  static {
    val exceptionHandler = DIContext.INSTANCE.get(GlobalExceptionHandler.class);
    setDefaultUncaughtExceptionHandler(exceptionHandler);
  }

  @SneakyThrows
  public static void main(String[] args) {
    DIContext.INSTANCE.get(VisualCommand.class).run();
  }
}
