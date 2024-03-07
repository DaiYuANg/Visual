package org.visual.debugger.component;

import java.util.Map;
import javafx.scene.control.ListView;

class SystemPropertiesListView extends ListView<Map.Entry<Object, Object>> {
  public SystemPropertiesListView() {
    setCellFactory(new SystemPropertiesCellFactory());
  }
  //  init {
  //    cellFactory = SystemPropertiesCellFactory()
  //  }
}
