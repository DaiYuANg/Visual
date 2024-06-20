package org.visual.component;

import javafx.scene.control.DialogPane;
import javafx.scene.control.SplitPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationPane extends DialogPane {

  private final SplitPane splitPane =
      new SplitPane() {
        {
          setCache(true);
        }
      };
}
