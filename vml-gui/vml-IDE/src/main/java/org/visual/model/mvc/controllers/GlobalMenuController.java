package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.mvc.base.Controller;
import org.visual.model.mvc.views.Settings;

@Slf4j
public class GlobalMenuController implements Initializable {
	public MenuItem newProject;

	public void newProject(ActionEvent actionEvent) {
		val stage = new Stage();
		stage.centerOnScreen();
		DirectoryChooser directoryChooser = new DirectoryChooser();
	}

	public void openSettings(ActionEvent actionEvent) {
		Settings.SETTINGS.openSettings();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
