package org.visual.component.display;

import javafx.stage.Stage;

public class VisualStage extends Stage {

  public void showAndFocus() {
    requestFocus();
    show();
  }
}
