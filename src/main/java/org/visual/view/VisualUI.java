/* (C)2024*/
package org.visual.view;

import static io.smallrye.mutiny.Uni.createFrom;
import static java.lang.Thread.setDefaultUncaughtExceptionHandler;
import static javafx.application.Platform.setImplicitExit;
import static org.visual.context.DIContext.INSTANCE;

import atlantafx.base.theme.PrimerLight;
import java.util.concurrent.Executor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.exception.GlobalExceptionHandler;
import org.visual.store.api.HistoryRepository;

@Slf4j
public class VisualUI extends Application {
  private final String theme = new PrimerLight().getUserAgentStylesheet();

  @Override
  public void init() {
    log.info("UI init");
    setImplicitExit(false);
    val exceptionHandler = INSTANCE.get(GlobalExceptionHandler.class);
    setDefaultUncaughtExceptionHandler(exceptionHandler);
    Application.setUserAgentStylesheet(theme);
  }

  @SneakyThrows
  @Override
  public void start(@NotNull Stage stage) {
    createFrom()
        .item(INSTANCE.get(HistoryRepository.class).queryHistory())
        .log()
        .emitOn(INSTANCE.get(Executor.class))
        .invoke(his -> log.atInfo().log("history:{}", his))
        .subscribe()
        .with(w -> {}, t -> {});
    log.atInfo().log("UI Started");
    val scene = INSTANCE.get(Scene.class);
    stage.setScene(scene);
    stage.setWidth(800.0);
    stage.setHeight(600.0);
    stage.show();
    stage.requestFocus();
  }
}
