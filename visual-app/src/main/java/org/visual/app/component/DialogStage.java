package org.visual.app.component;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DialogStage extends Stage {

  public DialogStage() {
    super();
    setup();
  }

  private void setup() {
    initModality(Modality.APPLICATION_MODAL);
    initStyle(StageStyle.UTILITY);
    onShownProperty().addListener((observable, oldValue, newValue) -> {
      requestFocus();
    });
  }
}
