package org.visual.debugger.component;

import java.util.Map;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SystemPropertiesCellFactory
    implements Callback<ListView<Map.Entry<Object, Object>>, ListCell<Map.Entry<Object, Object>>> {

  @Override
  public ListCell<Map.Entry<Object, Object>> call(ListView<Map.Entry<Object, Object>> param) {
    return new ListCell<Map.Entry<Object, Object>>() {
      @Override
      protected void updateItem(Map.Entry<Object, Object> item, boolean empty) {
        super.updateItem(item, empty);
        setText(item != null ? STR."\{item.getKey()}=\{item.getValue()}" : "");
      }
    };
  }
}
