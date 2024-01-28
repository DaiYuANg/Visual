package org.visual.component.loading;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.visual.component.font.FontManager;
import org.visual.component.font.FontUsages;
import org.visual.component.layout.VPadding;
import org.visual.component.theme.Theme;

public class LoadingPane extends Pane {
  private final Label label =
      new Label() {
        {
          FontManager.get().setFont(FontUsages.loading, this);
          setTextFill(Theme.current().normalTextColor());
        }
      };
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
