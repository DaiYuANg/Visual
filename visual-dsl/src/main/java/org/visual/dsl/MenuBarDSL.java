package org.visual.dsl;

import javafx.scene.control.MenuBar;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MenuBarDSL {

  public class MenuBarBuilder extends BaseBuilder<MenuBar> {

    public MenuBarBuilder() {
      super(new MenuBar());
    }
  }
}
