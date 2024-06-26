package org.visual.factory;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Singleton
public class StageProvider implements Provider<Stage> {
  @Override
  public Stage get() {
    val stage = new Stage();
    stage.setTitle("Visual");
    return stage;
  }
}
