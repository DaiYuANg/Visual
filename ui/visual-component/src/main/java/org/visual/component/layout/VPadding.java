package org.visual.component.layout;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class VPadding extends Pane {
  public VPadding(double padding) {
    setVisible(false);
    setWidth(0.0);
    setPrefWidth(0.0);
    setMaxWidth(0.0);
    setPadding(new Insets(0.0, 0.0, padding, 0.0));
  }
}
