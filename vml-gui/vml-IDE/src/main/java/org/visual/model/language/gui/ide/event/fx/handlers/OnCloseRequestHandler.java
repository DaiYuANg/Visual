package org.visual.model.language.gui.ide.event.fx.handlers;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class OnCloseRequestHandler implements EventHandler<WindowEvent> {
	private final Stage stage;

	public OnCloseRequestHandler(final Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(WindowEvent event) {
		val alert = new Alert(Alert.AlertType.CONFIRMATION);
		if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
			stage.close();
			return;
		}
		event.consume();
	}
}
