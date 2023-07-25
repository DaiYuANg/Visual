package org.visual.model.mvc.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import lombok.val;
import org.visual.model.base.Controller;
import org.visual.model.contexts.ProjectContext;

public class MainSceneController implements Controller {
    //    public ToggleButton leftToggleBtn;
    public SplitPane splitPane;
    public VBox leftDrawer;
    public VBox centerPane;
    public VBox rightDrawer;
    public VBox rootVBox;
    public TreeView<String> tree;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

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
}
