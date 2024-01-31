package org.visual.database;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.display.VisualStage;
import org.visual.database.constant.FXMLKey;
import org.visual.database.context.VisualDatabaseContext;

@RequiredArgsConstructor
@Slf4j
public class VisualDatabaseUI extends Application {

  private final VisualStage rootStage = VisualDatabaseContext.INSTANCE.get(VisualStage.class);

  private final Parent layout = VisualDatabaseContext.INSTANCE.load(FXMLKey.LAYOUT);

  private final Scene rootScene = new Scene(layout);

  {
    rootStage.setScene(rootScene);
  }

  @SneakyThrows
  @Override
  public void start(Stage primaryStage) {
    rootStage.show();
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    Runtime.getRuntime().exit(0);
  }
}
