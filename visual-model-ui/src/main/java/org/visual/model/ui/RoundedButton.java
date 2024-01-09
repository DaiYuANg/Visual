package org.visual.model.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class RoundedButton extends Button {

    private final Circle circle;

    private final Label label;

    public RoundedButton(String buttonText) {
        super();
        this.circle = createCircle();
        this.label = createLabel(buttonText);
        initialize();
    }

    private @NotNull Circle createCircle() {
        val newCircle = new Circle();
        newCircle.setRadius(10);
        newCircle.setFill(Color.LIGHTGRAY);
        newCircle.setStroke(Color.BLACK);
        return newCircle;
    }

    private @NotNull Label createLabel(String text) {
        Label newLabel = new Label(text);
        newLabel.setTextFill(Color.BLACK);
        return newLabel;
    }

    private void initialize() {
        val stackPane = new StackPane(circle, label);
        setGraphic(stackPane);
        setPadding(new Insets(5));
    }
}
