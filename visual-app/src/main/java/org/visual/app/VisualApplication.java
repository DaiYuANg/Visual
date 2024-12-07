/* (C)2024*/
package org.visual.app;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.command.PicoFactory;
import org.visual.app.command.VisualCommand;
import org.visual.app.context.DIContext;
import org.visual.app.exception.GlobalExceptionHandler;
import picocli.CommandLine;

import static java.lang.System.exit;
import static java.lang.Thread.setDefaultUncaughtExceptionHandler;

@Slf4j
public class VisualApplication {
  static {
    val exceptionHandler = DIContext.INSTANCE.get(GlobalExceptionHandler.class);
    setDefaultUncaughtExceptionHandler(exceptionHandler);
  }

  @SneakyThrows
  public static void main(String[] args) {
    log.atDebug().log("Visual Start");
    val commandLine = new VisualCommand(args);
    val exitCode = new CommandLine(commandLine, new PicoFactory()).execute(args);
    exit(exitCode);
  }
}
