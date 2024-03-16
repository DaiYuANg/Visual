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
import org.visual.constant.FXMLView;
import org.visual.context.DIContext;
import org.visual.context.UIContext;
import org.visual.handle.GlobalExceptionHandler;

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
  public void start(Stage stage) {
    log.atInfo().log("UI Started");
    val rootStage = DIContext.INSTANCE.get(Stage.class);

    val rootScene = DIContext.INSTANCE.get(Scene.class);

    val rootFxml = UIContext.INSTANCE.load(FXMLView.MAIN_LAYOUT);

    rootScene.setRoot(rootFxml);

    log.info(rootScene.getRoot().toString());

    rootStage.setScene(rootScene);

    rootStage.showAndWait();
  }
}
