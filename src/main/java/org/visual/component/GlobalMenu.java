package org.visual.component;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Singleton
@Slf4j
@Lazy
public class GlobalMenu extends MenuBar {

  private final Menu menu =
      new Menu("File") {
        {
          val fileItem = new MenuItem("File");
          getItems().add(fileItem);
        }
      };

  {
    setUseSystemMenuBar(true);
    getMenus().add(menu);
  }
}
