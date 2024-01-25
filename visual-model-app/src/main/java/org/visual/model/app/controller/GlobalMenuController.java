package org.visual.model.app.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.visual.model.debugger.VisualModelDebugger;
import org.visual.model.debugger.inspector.FXComponentInspectorHandler;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
public class GlobalMenuController implements Initializable {

    @Inject
    Stage rootStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openDebugger() {
        VisualModelDebugger.show(rootStage.getScene());
    }

    public void enableInspector(){
        FXComponentInspectorHandler.handleAll();
    }
}
