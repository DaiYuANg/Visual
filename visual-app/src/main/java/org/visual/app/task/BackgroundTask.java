package org.visual.app.task;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class BackgroundTask extends AbstractExecutionThreadService {

  private final PrintDialogTask printDialogTask;

  private final Uni<Vertx> vertx;

  @Override
  protected void run() throws Exception {
    log.atInfo().log("Run background task");
    vertx.invoke(v -> v.setPeriodic(printDialogTask.delay(), printDialogTask)).subscribe().with(t -> {

    });
  }
}
