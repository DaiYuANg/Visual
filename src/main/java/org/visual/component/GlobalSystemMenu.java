package org.visual.component;

import java.util.function.Supplier;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import lombok.val;

public class GlobalSystemMenu {

  private final Supplier<MenuItem> exitItem =
      () -> {
        val menuItem = new MenuItem("exit");
        menuItem.acceleratorProperty().set(KeyCombination.keyCombination("CTRL+Q"));
        menuItem.setOnAction(actionEvent -> {});
        return menuItem;
      };
}
