package org.visual.component.display;

import javafx.scene.control.Label;
import org.visual.component.font.FontManager;
import org.visual.component.theme.Theme;

public class ThemeLabel extends Label {
  public ThemeLabel() {
    setTextFill(Theme.current().normalTextColor());
  }

  public ThemeLabel(String text) {
    super(text);
    setTextFill(Theme.current().normalTextColor());
    FontManager.get().setFont(this);
  }
}
