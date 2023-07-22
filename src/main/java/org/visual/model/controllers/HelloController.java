package org.visual.model.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HelloController {
    public ToggleButton leftToggleBtn;
    public SplitPane splitPane;
    public VBox leftDrawer;
    public VBox centerPane;
    public VBox rightDrawer;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        leftToggleBtn.onMouseClickedProperty().addListener((observable, oldValue, newValue) -> {
            System.err.println(newValue);
        });
    }

    public void toggleLeftDrawer(ActionEvent actionEvent) {}
}
