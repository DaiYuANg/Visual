package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@Factory
public class UIFactory {

  @Bean
  Stage stage() {
    return new Stage();
  }

  @Bean
  Scene scene() {
    return new Scene(new Pane());
  }
}
