package org.visual.component.display;

import javafx.scene.layout.Pane;
import org.visual.shared.OS;

public class SystemTitleBar extends Pane {
  public SystemTitleBar() {
    Pane titleBar =
        switch (OS.OS) {
          case OS.MAC -> new MacOSTitleBar();
          case OS.LINUX -> new LinuxTitleBar();
          case OS.WINDOWS -> new WindowsTitleBar();
          default -> throw new UnsupportedOperationException("NOT SUPPORT PLATFORM");
        };
    getStyleClass().add("title-rounded");
    setHeight(50.0);
    getChildren().add(titleBar);
  }
}
