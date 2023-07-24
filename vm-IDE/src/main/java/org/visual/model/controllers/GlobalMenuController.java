package org.visual.model.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class GlobalMenuController {
    public MenuItem newProject;

    public void newProject(ActionEvent actionEvent) {
        val stage = new Stage();
        stage.centerOnScreen();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        val directory = directoryChooser.showDialog(stage);
        System.err.println(directory);
    }

    public void openSettings(ActionEvent actionEvent) {
        val settings = new Stage();
        val bounds = Screen.getPrimary().getBounds();
        settings.setWidth(bounds.getWidth() * 0.5);
        settings.setHeight(bounds.getHeight() * 0.8);
        settings.setTitle("Settings");
        log.info("open settings");
        settings.showAndWait();
    }
}
