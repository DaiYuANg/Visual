package org.visual.debugger.component;

import java.util.Map;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemPropertiesCellFactory
    implements Callback<ListView<Map.Entry<Object, Object>>, ListCell<Map.Entry<Object, Object>>> {

  @Override
  public ListCell<Map.Entry<Object, Object>> call(ListView<Map.Entry<Object, Object>> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(Map.Entry<Object, Object> item, boolean empty) {
        super.updateItem(item, empty);
        setText(item != null ? item.getKey().toString() + item.getValue().toString() : "");
      }
    };
  }
}
