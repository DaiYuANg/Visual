package org.visual.component;

import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationPane extends DialogPane {

  private final SplitPane splitPane =
      new SplitPane() {
        {
          setCache(true);
        }
      };

  private final ListView<String> left =
      new ListView<>() {
        {
          getItems().add("test");
        }
      };

  private final ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);

  {
    splitPane.getItems().add(left);
    setGraphic(splitPane);
    getButtonTypes().add(confirm);
  }
}
