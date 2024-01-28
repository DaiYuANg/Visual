package org.visual.component.title;


import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import org.visual.component.display.CommonTitleBar;

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
                maximize();
            }
        });

        minimizeButton.setOnAction(event -> maximize());
        getChildren().addAll(closeButton, sizeableButton, minimizeButton);
    }
}
