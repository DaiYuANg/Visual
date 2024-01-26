package org.visual.model.debugger.component

import javafx.scene.control.ListView

class SystemPropertiesListView : ListView<Map.Entry<Any, Any>>() {
  init {
    cellFactory = SystemPropertiesCellFactory()
  }
}
