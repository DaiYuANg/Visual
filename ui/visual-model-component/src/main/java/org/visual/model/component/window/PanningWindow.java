package org.visual.model.component.window;

import java.util.Optional;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PanningWindow extends Region {
    private final DoubleProperty contentX = new SimpleDoubleProperty(0.0);
    private final DoubleProperty contentY = new SimpleDoubleProperty(0.0);
    private final DoubleProperty zoom = new SimpleDoubleProperty(1.0);

    public PanningWindow() {
        // Set up initial properties
        setManaged(false);
        setStyle("-fx-background-color: white;");

        // Add a sample content (you can replace this with your own content)
        Rectangle sampleContent = new Rectangle(2000.0, 2000.0, Color.AQUA);
        sampleContent.relocate(0.0, 0.0);
        getChildren().add(sampleContent);

        // Add event handlers
        setOnMousePressed(this::handleMousePressed);
        setOnMouseDragged(this::handleMouseDragged);
        setOnScroll(this::handleScroll);
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }

    private void handleMousePressed(MouseEvent event) {
        Optional.ofNullable(getScene()).ifPresent(scene -> {
            scene.setCursor(Cursor.MOVE);
            contentX.set(contentX.get() - (event.getSceneX() - contentX.get()));
            contentY.set(contentY.get() - (event.getSceneY() - contentY.get()));
        });
    }

    private void handleMouseDragged(MouseEvent event) {
        Optional.ofNullable(getScene()).ifPresent(scene -> {
            double deltaX = event.getSceneX() - contentX.get();
            double deltaY = event.getSceneY() - contentY.get();
            contentX.set(event.getSceneX());
            contentY.set(event.getSceneY());
            relocate(relocateX(deltaX), relocateY(deltaY));
        });
    }

    private void handleScroll(ScrollEvent event) {
        double zoomFactor = (event.getDeltaY() > 0) ? 1.1 : 0.9;
        zoom.set(zoom.get() * zoomFactor);
        javafx.geometry.Point2D delta = sceneToLocal(event.getSceneX(), event.getSceneY());
        contentX.set(contentX.get() - delta.getX() * (zoomFactor - 1));
        contentY.set(contentY.get() - delta.getY() * (zoomFactor - 1));
        relocate(relocateX(delta.getX() * (zoomFactor - 1)), relocateY(delta.getY() * (zoomFactor - 1)));
    }

    private double relocateX(double deltaX) {
        double newX = contentX.get() + deltaX;
        double maxX = Math.max(0.0, Math.min(newX, getWidth() - scaledContentWidth()));
        return (maxX == 0.0 || maxX == getWidth() - scaledContentWidth()) ? contentX.get() : newX;
    }

    private double relocateY(double deltaY) {
        double newY = contentY.get() + deltaY;
        double maxY = Math.max(0.0, Math.min(newY, getHeight() - scaledContentHeight()));
        return (maxY == 0.0 || maxY == getHeight() - scaledContentHeight()) ? contentY.get() : newY;
    }

    private double scaledContentWidth() {
        return getWidth() / zoom.get();
    }

    private double scaledContentHeight() {
        return getHeight() / zoom.get();
    }
}
