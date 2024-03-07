package org.visual.debugger.component;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.WindowEvent;
import org.visual.component.display.SystemMenuBar;

public class GlobalMenuBar extends SystemMenuBar {

  private final MenuItem exitItem;
  private final Menu fileMenu;
  private final CheckMenuItem showBoundsCheckbox;
  private final CheckMenuItem collapseControls;
  private final MenuItem aboutItem;
  private final Menu aboutMenu;

  public GlobalMenuBar(EventHandler<ActionEvent> exitHandle) {
    this.exitItem = new MenuItem("Exit");
    this.fileMenu = new Menu("File");
    this.showBoundsCheckbox = new CheckMenuItem("Show Bounds Overlays");
    this.collapseControls = new CheckMenuItem("Collapse controls In Tree");
    this.aboutItem = new MenuItem("About");
    this.aboutMenu = new Menu("Help");

    exitItem.setAccelerator(KeyCombination.keyCombination("CTRL+Q"));
    exitItem.setOnAction(exitHandle);

    showBoundsCheckbox
        .selectedProperty()
        .addListener(
            (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
              // setStatusText(newValue ? toolTipSelected : toolTipNotSelected, 4000);
            });

    showBoundsCheckbox.setId("show-bounds-checkbox");

    collapseControls
        .selectedProperty()
        .addListener(
            (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
              // setStatusText(newValue ? toolTipSelected : toolTipNotSelected, 4000);
            });

    collapseControls.setId("collapseControls");

    aboutItem.setOnAction(
        (EventHandler<ActionEvent>)
            event -> {
              // AboutBox.make("About", null);
            });

    aboutMenu.getItems().add(aboutItem);

    setId("global-menubar");
    addEventHandler(
        WindowEvent.WINDOW_SHOWN,
        (EventHandler<WindowEvent>)
            event -> {
              prefWidthProperty().bind(getScene().widthProperty());
            });

    getMenus().addAll(fileMenu, aboutMenu);
  }
}
