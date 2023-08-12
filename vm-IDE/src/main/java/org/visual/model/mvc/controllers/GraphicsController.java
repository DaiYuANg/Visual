package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.val;
import org.visual.model.components.DraggableNode;
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
		val b = new DraggableNode();
		b.getChildren();
		b.addNode(new Label("test"));
//		BorderStroke borderStroke = new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, BorderStroke.THIN);
//		Border border = new Border(borderStroke);
//		b.setBorder(border);
		b.setPadding(new Insets(10));
		root.getChildren().add(b);
	}
}
