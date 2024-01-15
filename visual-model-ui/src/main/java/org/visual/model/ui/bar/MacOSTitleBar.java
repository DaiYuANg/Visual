package org.visual.model.ui.bar;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MacOSTitleBar extends CommonTitleBar {

    private Button closeButton = new Button("close");
    private Button sizeableButton = new Button("size");
    private Button minimizeButton = new Button("min");

    {
        closeButton.setOnAction(event -> close());

        sizeableButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                restoreSizeOrMax();
            } else {
                maximizeWindow();
            }
        });

        minimizeButton.setOnAction(event -> minimizeWindow());
        getChildren().addAll(closeButton, sizeableButton, minimizeButton);
    }
}
