package org.visual.core;

import io.smallrye.mutiny.infrastructure.Infrastructure;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.LoggerFactory;

import static java.util.Optional.ofNullable;

@Slf4j
public class MutinySlf4jLogger implements Infrastructure.OperatorLogger {
  @Override
  public void log(String identifier, String event, Object value, Throwable failure) {
    val logger = LoggerFactory.getLogger(identifier);
    if (failure != null) {
      logger.atInfo().log("{}({})", event, failure.getClass().getName() + "(" + failure.getMessage() + ")");
      return;
    }
    ofNullable(value)
      .ifPresentOrElse(
        v -> logger.atInfo().log("{}({})", event, v),
        () -> logger.atInfo().log("{}()", event)
      );
  }
}
