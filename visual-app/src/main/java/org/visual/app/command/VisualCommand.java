package org.visual.app.command;

import io.avaje.inject.PostConstruct;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.inject.Singleton;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.lifecycle.Lifecycle;
import org.visual.app.util.SPI;
import org.visual.app.view.VisualUI;

import static io.smallrye.mutiny.Multi.createFrom;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class VisualCommand implements Runnable {


  @PostConstruct
  void init() {
    createFrom()
      .iterable(SPI.load(Lifecycle.class))
      .emitOn(Infrastructure.getDefaultExecutor())
      .onItem()
      .invoke(Lifecycle::onStart)
      .subscribe().with(t -> {
        log.atInfo().log(t.toString());
      });
  }

  @Override
  public void run() {
    Application.launch(VisualUI.class);
  }
}
