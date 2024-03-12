package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.nio.file.Path;
import javafx.stage.Stage;
import org.visual.component.window.Tray;

@Factory
public class UIFactory {

  @Bean
  Stage stage() {
    Stage rootStage = new Stage();
    rootStage.centerOnScreen();
    rootStage.setResizable(true);
    return rootStage;
  }

  @Bean
  Tray tray(Stage stage) {
    return new Tray(Path.of(""), stage);
  }
}
