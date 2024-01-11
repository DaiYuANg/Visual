package org.visual.model.ui.bar;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class TitleBar extends HBox {

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    private double xOffset = 0.0;
    private double yOffset = 0.0;

    {
        addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            this.getScene().getWindow().setX(e.getScreenX() - xOffset);
            this.getScene().getWindow().setY(e.getScreenY() - yOffset);
        });
        addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            this.getScene().getWindow().setOpacity(0.5);
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            this.getScene().getWindow().setOpacity(1.0);
        });
        setPadding(new Insets(0, 0, 0, 0));
    }

    protected void close() {
        val stage = getStage();
//        if () {
//            stage.setIconified(true);
//            return;
//        }
        getStage().close();
    }

    protected void maximizeWindow(@NotNull MouseEvent mouseEvent) {
        val stage = getStage();
        if (mouseEvent.getClickCount() == 2) {
            max(stage);
            stage.setFullScreen(true);
            return;
        }
        max(stage);
    }

    protected void minimizeWindow(MouseEvent mouseEvent) {
        getStage().setIconified(true);
    }

    private void max(@NotNull Stage stage) {
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

    protected Stage getStage() {
        return (Stage) getScene().getWindow();
    }
}
