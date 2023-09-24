package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.visual.model.modules.ITest;
import org.visual.model.mvc.base.Controller;

public class LayoutController implements Controller {
	public SplitPane splitPane;
	public VBox leftDrawer;
	public VBox rootVBox;
	public TreeView<String> tree;

	Vertx vertx;

	@Inject
	public LayoutController(ITest test){
		System.err.println(test);
	}

	@FXML
	public void initialize() {
		// splitPane.setDividerPositions(0,2,0.8);
		// val root = new
		// TreeItem<>(ProjectContext.PROJECT.getRoot().getAbsolutePath());
		// root.setExpanded(true);
		// val items = ProjectContext.PROJECT.getProjectFiles().stream()
		// .map(file -> new TreeItem<>(file.getAbsolutePath())).toList();
		// root.getChildren().addAll(items);
		// tree.setRoot(root);
		// tree.setShowRoot(true);
	}

	public void toggleLeftDrawer(ActionEvent actionEvent) {
	}

	@Override
	@Inject
	public void initialize(URL location, ResourceBundle resources) {
	}
}
