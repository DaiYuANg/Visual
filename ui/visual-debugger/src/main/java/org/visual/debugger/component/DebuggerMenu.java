package org.visual.debugger.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class DebuggerMenu extends Menu {

  private final MenuItem openDebugger = new MenuItem("Open Debugger");

  public DebuggerMenu() {
    setText("Debugger");

    openDebugger.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            // 在这里添加 openDebugger 被点击时的处理逻辑
          }
        });

    getItems().addAll(openDebugger);
  }
}
