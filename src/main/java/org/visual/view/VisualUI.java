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
  private final GlobalExceptionHandler exceptionHandler =
      DIContext.INSTANCE.get(GlobalExceptionHandler.class);

  private final String theme = new PrimerLight().getUserAgentStylesheet();

  private final HistoryRepository historyRepository =
      DIContext.INSTANCE.get(HistoryRepository.class);

  @Override
  public void init() {
    Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    Application.setUserAgentStylesheet(theme);
    //            rootScene.getStylesheets().addAll("/help.css", "/theme.css");
    //    rootScene.setFill(Color.TRANSPARENT);
  }

  @SneakyThrows
  @Override
  public void start(Stage stage) {
    val rootStage = DIContext.INSTANCE.get(Stage.class);
    historyRepository
        .findLatestHistory()
        .ifPresentOrElse(
            history -> {
              log.atDebug().log("history not found");
              val rootFxml = UIContext.INSTANCE.load(FXMLView.MAIN_LAYOUT);
              val rootScene = new Scene(rootFxml);
              rootStage.setScene(rootScene);
              //      open history
            },
            () -> {
              log.atDebug().log("Not found history");
              val creationFXML = UIContext.INSTANCE.load(FXMLView.CREATION);
              val creationScene = new Scene(creationFXML);
              rootStage.setScene(creationScene);
            });
    rootStage.show();
  }
}
