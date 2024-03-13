package org.visual.provider;

import jakarta.inject.Provider;
import javafx.stage.Stage;
import lombok.val;

public class StageProvider implements Provider<Stage> {
  @Override
  public Stage get() {
    val rootStage = new Stage();
    rootStage.centerOnScreen();
    rootStage.setResizable(true);
    return rootStage;
  }
}
