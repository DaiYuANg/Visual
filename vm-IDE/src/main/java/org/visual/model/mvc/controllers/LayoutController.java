package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import lombok.val;
import org.visual.model.contexts.ProjectContext;
import org.visual.model.mvc.base.Controller;

public class LayoutController implements Controller {
	public SplitPane splitPane;
	public VBox leftDrawer;
	public VBox rootVBox;
	public TreeView<String> tree;

	@FXML
	public void initialize() {
		val root = new TreeItem<>(ProjectContext.PROJECT.getRoot().getAbsolutePath());
		root.setExpanded(true);
		val items = ProjectContext.PROJECT.getProjectFiles().stream()
				.map(file -> new TreeItem<>(file.getAbsolutePath())).toList();
		root.getChildren().addAll(items);
		tree.setRoot(root);
		tree.setShowRoot(true);
	}

	public void toggleLeftDrawer(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
