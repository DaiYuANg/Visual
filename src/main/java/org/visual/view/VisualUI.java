/* (C)2024*/
package org.visual.view;

import static java.lang.Thread.setDefaultUncaughtExceptionHandler;
import static javafx.application.Platform.setImplicitExit;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.component.InitializationDialog;
import org.visual.context.DIContext;
import org.visual.exception.GlobalExceptionHandler;
import org.visual.i18n.I18n;
import org.visual.i18n.constant.Action;
import org.visual.store.api.HistoryRepository;

@Slf4j
public class VisualUI extends Application {
  private final String theme = new PrimerLight().getUserAgentStylesheet();

  @Override
  public void init() {
    log.info("UI init");
    setImplicitExit(false);
    val exceptionHandler = DIContext.INSTANCE.get(GlobalExceptionHandler.class);
    setDefaultUncaughtExceptionHandler(exceptionHandler);
    Application.setUserAgentStylesheet(theme);
  }

  @SneakyThrows
  @Override
  public void start(@NotNull Stage stage) {
    log.atInfo().log("UI Started");
    val scene = DIContext.INSTANCE.get(Scene.class);
    log.atInfo().log(I18n.INSTANCE.t(Action.CONFIRM));
    val w = DIContext.INSTANCE.get(HistoryRepository.class).queryHistory();
    if (w.isEmpty()) {
      new InitializationDialog().showAndWait();
    }
    stage.setScene(scene);
    stage.setWidth(800.0);
    stage.setHeight(600.0);
    stage.show();
    stage.requestFocus();
  }
}
