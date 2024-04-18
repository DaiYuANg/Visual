package org.visual.provider;

import jakarta.inject.Provider;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class StageProvider implements Provider<Stage> {
  @Override
  public Stage get() {
    val stage = new Stage();
    stage.setTitle("Visual");
    //    stage.initStyle(StageStyle.TRANSPARENT);
    return stage;
  }
}
