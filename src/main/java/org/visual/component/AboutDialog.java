package org.visual.component;

import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AboutDialog extends Dialog<Void> {
  public AboutDialog() {
    setTitle("About");
    setHeaderText("Application Information");
    initStyle(StageStyle.UTILITY);
  }
}
