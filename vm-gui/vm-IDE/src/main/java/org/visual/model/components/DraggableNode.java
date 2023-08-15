package org.visual.model.components;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class DraggableNode extends BaseContainer {

	private double xOffset = 0;

	private double yOffset = 0;

	public DraggableNode(List<Node> nodes) {
		getChildren().addAll(nodes);
		setup();
	}

	public DraggableNode(Node... nodes) {
		getChildren().addAll(nodes);
		setup();
	}

	private void setup() {
		setOnMousePressed(this::handleMousePressed);
		setOnMouseDragged(this::handleMouseDragged);
	}

	private void handleMousePressed(@NotNull MouseEvent event) {
		xOffset = event.getSceneX() - getLayoutX();
		yOffset = event.getSceneY() - getLayoutY();
	}

	private void handleMouseDragged(@NotNull MouseEvent event) {
		setLayoutX(event.getSceneX() - xOffset);
		setLayoutY(event.getSceneY() - yOffset);
	}
}
