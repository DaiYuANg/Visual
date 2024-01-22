package org.visual.model.component.bar;


import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
public class MacOSTitleBar extends CommonTitleBar {

    private final Button closeButton = new Button("close");
    private final Button sizeableButton = new Button("size");
    private final Button minimizeButton = new Button("min");

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
