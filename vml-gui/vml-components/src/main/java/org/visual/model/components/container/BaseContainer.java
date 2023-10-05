package org.visual.model.components.container;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BaseContainer extends Pane {

	public void addNode(Node... nodes) {
		// Executors.new
		getChildren().addAll(nodes);
	}
}
