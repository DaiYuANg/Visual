package org.visual.app.view

import javafx.beans.property.SimpleStringProperty

class TableFieldViewModule {
  private val fieldType = SimpleStringProperty()
  var type: String
    get() = fieldType.value
    set(value) {
      fieldType.value = value
    }
}