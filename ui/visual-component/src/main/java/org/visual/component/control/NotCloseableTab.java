package org.visual.component.control;

import javafx.scene.control.Tab;

class NotCloseableTab extends Tab {

  NotCloseableTab() {
    setClosable(false);
  }
}
