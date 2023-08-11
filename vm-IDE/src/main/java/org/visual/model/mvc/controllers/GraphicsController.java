package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.val;
import org.visual.model.mvc.base.Controller;

public class GraphicsController implements Controller {
	public AnchorPane root;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void initialize() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		val b = new BorderPane();
		// b.setBorder(Border.stroke());
		b.setCenter(new Label("test"));
		BorderStroke borderStroke = new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, BorderStroke.THIN);
		Border border = new Border(borderStroke);
		b.setBorder(border);
		b.setPadding(new Insets(10));
		b.setOnMousePressed(event -> {
			xOffset = event.getSceneX() - b.getLayoutX();
			yOffset = event.getSceneY() - b.getLayoutY();
		});
		b.setOnMouseDragged(event -> {
			b.setLayoutX(event.getSceneX() - xOffset);
			b.setLayoutY(event.getSceneY() - yOffset);
		});
		root.getChildren().add(b);
	}
}
