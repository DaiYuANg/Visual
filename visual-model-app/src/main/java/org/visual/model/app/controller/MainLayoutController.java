package org.visual.model.app.controller;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.val;
import org.visual.model.ui.control.IconButton;
//import org.visual.model.ui.bar.SystemTitleBar;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
public class MainLayoutController implements Initializable {
    public IconButton test;
//    @FXML
//    public SystemTitleBar titleBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.err.println(test.getIcon());
    }
}
