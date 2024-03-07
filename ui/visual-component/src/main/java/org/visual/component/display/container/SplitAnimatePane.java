package org.visual.component.display.container;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.SplitPane;

public class SplitAnimatePane extends SplitPane {
  private final SimpleBooleanProperty collapse = new SimpleBooleanProperty(false);

  public Boolean getCollapse() {
    return collapse.get();
  }

  public void setCollapse(Boolean value) {
    if (value != null) {
      collapse.set(value);
    }
  }

  public void toggle() {
    collapse.set(collapse.get());
  }
}
