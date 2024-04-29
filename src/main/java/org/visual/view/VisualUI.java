/* (C)2024*/
package org.visual.view;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.context.DIContext;
import org.visual.exception.GlobalExceptionHandler;

@Slf4j
public class VisualUI extends Application {
  private final String theme = new PrimerLight().getUserAgentStylesheet();

  @Override
  public void init() {
    log.info("UI init");
    Platform.setImplicitExit(false);
    val exceptionHandler = DIContext.INSTANCE.get(GlobalExceptionHandler.class);
    Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    Application.setUserAgentStylesheet(theme);
  }

  @SneakyThrows
  @Override
  public void start(@NotNull Stage stage) {
    log.atInfo().log("UI Started");
    val scene = DIContext.INSTANCE.get(Scene.class);
    stage.setScene(scene);
    stage.setWidth(800.0);
    stage.setHeight(600.0);
    stage.show();
    stage.requestFocus();
  }
}
