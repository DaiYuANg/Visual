package org.visual.model.debugger.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class DebuggerLayoutController implements Initializable {

    @Inject
    Preferences preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
