package org.visual.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class TabsController implements Initializable {
    @FXML
    TabPane tabPane;
    @FXML
    Tab overviewTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
