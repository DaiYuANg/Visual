package org.visual.component;

import javafx.scene.control.Dialog;

public class AboutDialog extends Dialog<Void> {
  public AboutDialog() {
    setTitle("About");
    setHeaderText("Application Information");
  }
}
