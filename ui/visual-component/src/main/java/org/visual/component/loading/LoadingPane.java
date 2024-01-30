package org.visual.component.loading;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.visual.component.layout.VPadding;

public class LoadingPane extends Pane {
  private final Label label = new Label() {};
  @Getter private final VProgressBar progressBar = new VProgressBar();

  public LoadingPane(String defaultText) {
    label.setText(defaultText);
    getChildren().add(new VBox(label, new VPadding(15), progressBar));
    progressBar.setCurrentLoadingItemCallback(item -> label.setText(item.name));
  }

  public void setLength(double length) {
    progressBar.setLength(length);
  }
}
