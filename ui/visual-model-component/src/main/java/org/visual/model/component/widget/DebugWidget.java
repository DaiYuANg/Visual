package org.visual.model.component.widget;

import javafx.scene.layout.StackPane;

public class DebugWidget extends StackPane {

    public DebugWidget() {
        setLayoutX(10.0);
        setLayoutY(10.0);
        setPrefSize(50, 50);
        FpsWidget fpsWidget = new FpsWidget();
        getChildren().add(fpsWidget);
        toFront();
    }
}
