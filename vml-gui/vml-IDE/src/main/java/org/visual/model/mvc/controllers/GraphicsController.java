package org.visual.model.mvc.controllers;

import com.google.inject.Inject;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.components.DraggableNode;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class GraphicsController implements Initializable {
	public AnchorPane root;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	@Inject
	public void initialize(URL location, ResourceBundle resources) {
		log.info("graphics controller init");
		val b = new DraggableNode();
		b.getChildren();
		b.addNode(new Label("test"));
		b.setPadding(new Insets(10));
		root.getChildren().add(b);
	}
}
