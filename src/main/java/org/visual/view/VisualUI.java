/* (C)2024*/
package org.visual.view;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.FXMLView;
import org.visual.context.DIContext;
import org.visual.context.UIContext;
import org.visual.handle.GlobalExceptionHandler;
import org.visual.local.store.api.HistoryRepository;

@Slf4j
public class VisualUI extends Application {
  private final String theme = new PrimerLight().getUserAgentStylesheet();

  private final HistoryRepository historyRepository =
      DIContext.INSTANCE.get(HistoryRepository.class);

  @Override
  public void init() {
    val exceptionHandler = DIContext.INSTANCE.get(GlobalExceptionHandler.class);
    Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    Application.setUserAgentStylesheet(theme);
  }

  @SneakyThrows
  @Override
  public void start(Stage stage) {
    val rootStage = DIContext.INSTANCE.get(Stage.class);
    val rootFxml = UIContext.INSTANCE.load(FXMLView.MAIN_LAYOUT);
    val rootScene = new Scene(rootFxml);
    rootStage.setScene(rootScene);
    rootStage.show();
  }
}
