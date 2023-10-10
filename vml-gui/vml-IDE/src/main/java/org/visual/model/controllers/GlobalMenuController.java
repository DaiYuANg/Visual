package org.visual.model.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.views.stages.SettingsViewStage;

@Slf4j
public class GlobalMenuController implements Initializable {
	public MenuItem newProject;

	@Inject
	private SettingsViewStage settingsViewStage;

	public void newProject(ActionEvent actionEvent) {
		val stage = new Stage();
		stage.centerOnScreen();
	}

	public void openSettings(ActionEvent actionEvent) {
		settingsViewStage.openSettings();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.err.println(location);
	}
}
