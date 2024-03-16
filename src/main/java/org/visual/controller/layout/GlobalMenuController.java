package org.visual.controller.layout;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

@Singleton
public class GlobalMenuController implements Initializable {

  @FXML MenuItem enableInspectorMenu;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void openDebugger() {
    //    VisualDebuggerView.show(rootStage.getScene());
  }

  public void enableInspector() {
    enableInspectorMenu.setText("Disable Inspector");
    //    FXComponentInspectorHandler.handleAll();
  }
}
