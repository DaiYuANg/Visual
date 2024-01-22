package org.visual.model.debugger.controller;

import io.avaje.inject.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Slf4j
@Singleton
public class DebuggerLayoutController implements Initializable {

    @Inject
    Preferences preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
