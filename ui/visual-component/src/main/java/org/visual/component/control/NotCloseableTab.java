package org.visual.component.control;

import javafx.scene.control.Tab;

public class NotCloseableTab extends Tab {

  public NotCloseableTab() {
    setClosable(false);
  }
}
