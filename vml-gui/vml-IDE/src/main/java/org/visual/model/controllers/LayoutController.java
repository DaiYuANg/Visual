package org.visual.model.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.visual.model.contexts.ApplicationContext;
import org.visual.model.services.IOperationSystemService;
import org.visual.model.services.IPreferenceService;

public class LayoutController implements Initializable{
	public SplitPane splitPane;
	public VBox leftDrawer;
	public VBox rootVBox;
	public TreeView<String> tree;

	@Inject
	private Vertx vertx;

	@Inject
	private IOperationSystemService operationSystemService;

	@Inject
	private IPreferenceService preferenceService;

//	@Inject
	public LayoutController(){
		System.err.println(ApplicationContext.INSTANCE.hashCode());
	}

//	@FXML
//	public void initialize() {
//		System.err.println(iTest.aa());
//		// splitPane.setDividerPositions(0,2,0.8);
//		// val root = new
//		// TreeItem<>(ProjectContext.PROJECT.getRoot().getAbsolutePath());
//		// root.setExpanded(true);
//		// val items = ProjectContext.PROJECT.getProjectFiles().stream()
//		// .map(file -> new TreeItem<>(file.getAbsolutePath())).toList();
//		// root.getChildren().addAll(items);
//		// tree.setRoot(root);
//		// tree.setShowRoot(true);
//	}

	public void toggleLeftDrawer(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.err.println(vertx);
		System.err.println(operationSystemService.getOperatingSystemType());
	}
}
