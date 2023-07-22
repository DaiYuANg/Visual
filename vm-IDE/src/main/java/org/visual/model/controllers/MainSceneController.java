package org.visual.model.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class MainSceneController {
//    public ToggleButton leftToggleBtn;
    public SplitPane splitPane;
    public VBox leftDrawer;
    public VBox centerPane;
    public VBox rightDrawer;
    public VBox rootVBox;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {

//        splitPane.getChildrenUnmodifiable().add(new MenuBar());
//        leftToggleBtn.onMouseClickedProperty().addListener((observable, oldValue, newValue) -> {
//            System.err.println(newValue);
//        });
    }

    public void toggleLeftDrawer(ActionEvent actionEvent) {}
}
