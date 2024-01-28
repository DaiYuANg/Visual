package org.visual.component.shapes;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.component.control.drag.DragHandler;
import org.visual.component.font.FontManager;
import org.visual.component.font.FontUsages;
import org.visual.component.pojo.Point;
import org.visual.component.util.FXUtils;

public class MovablePoint extends Group {
    public MovablePoint(String labelText) {
        var point = new Circle(5);
        point.setStrokeWidth(2);
        point.setStroke(Color.RED);
        point.setFill(Color.TRANSPARENT);
        var dot = new Circle(2);
        dot.setFill(Color.RED);
        dot.setStrokeWidth(0);
        var label = new Label(labelText) {{
            FontManager.get().setFont(FontUsages.movableShapeLabel, this);
        }};
        point.setCursor(Cursor.MOVE);
        label.setTextFill(Color.RED);
        var wh = FXUtils.calculateTextBounds(label);
        label.setLayoutX(-wh.getWidth() / 2);
        label.setLayoutY(10);

        var handler = new DragHandler() {
            @Override
            protected void set(double x, double y) {
                setLayoutX(x);
                setLayoutY(y);
            }

            @Contract(" -> new")
            @Override
            protected double @NotNull [] get() {
                return new double[]{getLayoutX(), getLayoutY()};
            }
        };

        var pointAndDotGroup = new Group(point, dot);
        pointAndDotGroup.setOnMousePressed(handler);
        pointAndDotGroup.setOnMouseDragged(handler);

        getChildren().addAll(label, pointAndDotGroup);
    }

    public Point makePoint() {
        var point = new Point();
        point.x = getLayoutX();
        point.y = getLayoutY();
        return point;
    }

    public void from(@NotNull Point point) {
        setX(point.x);
        setY(point.y);
    }

    public void setX(double x) {
        setLayoutX(x);
    }

    public void setY(double y) {
        setLayoutY(y);
    }
}
