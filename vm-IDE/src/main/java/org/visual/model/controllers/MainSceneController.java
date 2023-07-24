package org.visual.model.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.val;
import org.visual.model.base.Controller;

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
        val root = new TreeItem<>("test");
        root.getChildren().addAll(new TreeItem<>("123"),new TreeItem<>("321"));
        tree.setRoot(root);
//        splitPane.getDividers().addListener((ListChangeListener<SplitPane.Divider>) c -> System.err.println(c));
//        splitPane.getChildrenUnmodifiable().add(new MenuBar());
//        leftToggleBtn.onMouseClickedProperty().addListener((observable, oldValue, newValue) -> {
//            System.err.println(newValue);
//        });
    }

    public void toggleLeftDrawer(ActionEvent actionEvent) {}
}
