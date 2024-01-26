package org.visual.model.app.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.visual.model.debugger.VisualModelDebugger;
import org.visual.model.debugger.inspector.FXComponentInspectorHandler;

@Singleton
public class GlobalMenuController implements Initializable {

    @FXML
    MenuItem enableInspectorMenu;

    @Inject
    Stage rootStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openDebugger() {
        VisualModelDebugger.show(rootStage.getScene());
    }

    public void enableInspector() {
        enableInspectorMenu.setText("Disable Inspector");
        FXComponentInspectorHandler.handleAll();
    }
}