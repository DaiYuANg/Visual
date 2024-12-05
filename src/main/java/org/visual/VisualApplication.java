/* (C)2024*/
package org.visual;

import io.smallrye.mutiny.infrastructure.Infrastructure;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.api.Lifecycle;
import org.visual.command.PicoFactory;
import org.visual.command.VisualCommand;
import org.visual.context.DIContext;
import org.visual.exception.GlobalExceptionHandler;
import org.visual.logger.MutinySlf4jLogger;
import org.visual.util.SPI;
import picocli.CommandLine;

import static io.smallrye.mutiny.Multi.createFrom;
import static io.smallrye.mutiny.infrastructure.Infrastructure.setOperatorLogger;
import static java.lang.System.exit;
import static java.lang.Thread.setDefaultUncaughtExceptionHandler;

@Slf4j
public class VisualApplication {
  static {
    setOperatorLogger(new MutinySlf4jLogger());
  }

  static {
    createFrom()
      .iterable(SPI.load(Lifecycle.class))
      .emitOn(Infrastructure.getDefaultExecutor())
      .onItem()
      .invoke(Lifecycle::onStart)
      .subscribe().with(t -> {
        log.atInfo().log(t.toString());
      });
  }

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
