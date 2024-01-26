package org.visual.model.component.control

import javafx.scene.control.Tab

class NotCloseableTab : Tab() {

  init {
    isClosable = false
  }
}
