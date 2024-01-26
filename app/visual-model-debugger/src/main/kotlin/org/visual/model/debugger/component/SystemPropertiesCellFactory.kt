package org.visual.model.debugger.component

import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback

class SystemPropertiesCellFactory :
    Callback<ListView<Map.Entry<Any, Any>>, ListCell<Map.Entry<Any, Any>>> {
  override fun call(p0: ListView<Map.Entry<Any, Any>>): ListCell<Map.Entry<Any, Any>> {

    return object : ListCell<Map.Entry<Any, Any>>() {
      override fun updateItem(p0: Map.Entry<Any, Any>?, p1: Boolean) {
        super.updateItem(p0, p1)
        text = "${p0?.key},${p0?.value}"
      }
    }
  }
}
