package org.visual.designer.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.visual.debugger.inspector.FXComponentInspectorHandler;
import org.visual.debugger.view.VisualDebuggerView;

@Singleton
public class GlobalMenuController implements Initializable {

  @FXML MenuItem enableInspectorMenu;

  @Inject Stage rootStage;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void openDebugger() {
    VisualDebuggerView.show(rootStage.getScene());
  }

  public void enableInspector() {
    enableInspectorMenu.setText("Disable Inspector");
    FXComponentInspectorHandler.handleAll();
  }
}
