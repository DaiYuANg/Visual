package org.visual.model.mvc.controllers;

import com.google.inject.Inject;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.components.DraggableNode;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class GraphicsController implements Initializable {
	public VBox root;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	@Inject
	public void initialize(URL location, ResourceBundle resources) {
		// 创建根节点
		TreeItem<String> rootNode = new TreeItem<>("Root");

		// 创建子节点
		TreeItem<String> child1 = new TreeItem<>("Child 1");
		TreeItem<String> child2 = new TreeItem<>("Child 2");

		// 将子节点添加到根节点
		rootNode.getChildren().addAll(child1, child2);

		// 创建TreeView并设置根节点
		TreeView<String> treeView = new TreeView<>(rootNode);
		root.getChildren().add(treeView);
	}
}
