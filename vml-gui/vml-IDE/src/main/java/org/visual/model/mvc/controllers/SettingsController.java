package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.mvc.base.Controller;

@Slf4j
public class SettingsController implements Initializable {

	public VBox settingList;

	public SplitPane splitPane;

	private final Double defaultSplitDriver = 0.2;

	private void lockSplitPane() {
		log.info("lock split panel");
		splitPane.setDividerPositions(defaultSplitDriver);
		splitPane.getDividers().forEach(d -> d.positionProperty().addListener((observable, oldValue, newValue) -> {
			splitPane.setDividerPosition(0, defaultSplitDriver);
		}));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
