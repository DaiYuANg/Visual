package org.visual.provider;

import jakarta.inject.Provider;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StageProvider implements Provider<Stage> {
  @Override
  public Stage get() {
    return new Stage();
  }
}
