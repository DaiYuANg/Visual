package org.visual.app.component;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.app.model.DatabaseObj;

@Slf4j
public class DataContent extends VBox {

  public DataContent(@NotNull DatabaseObj obj) {
    log.info("DataContent created");
    getChildren().addAll(new Label(obj.name()), new Label(obj.type()));
  }
}
