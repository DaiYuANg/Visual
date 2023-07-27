package org.visual.model.mvc.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.mvc.base.Controller;

@Slf4j
public class TitleBarController implements Controller {
    private double xOffset = 0;
    private double yOffset = 0;

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    @FXML
    private HBox titleBar;

    @Override
    public void initialize() {
        log.info("title bar init");
    }

    public void minimizeWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage) titleBar.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeWindow(@NotNull MouseEvent mouseEvent) {
        log.info("close window");
        val stage = (Stage)titleBar.getScene().getWindow();
        stage.close();
        mouseEvent.consume();
    }

    public void handleMousePressed(@NotNull MouseEvent mouseEvent) {
        titleBar.getScene().getWindow().setOpacity(0.5);
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    public void handleMouseDragged(@NotNull MouseEvent mouseEvent) {
        log.info("mouse drag dragged");
        titleBar.getScene().getWindow().setX(mouseEvent.getScreenX() - xOffset);
        titleBar.getScene().getWindow().setY(mouseEvent.getScreenY() - yOffset);
    }

    public void handleMouseDragReleased(MouseEvent mouseDragEvent) {
        log.info("mouse drag released");
        titleBar.getScene().getWindow().setOpacity(1.0);
    }

    public void maximizeWindow(@NotNull MouseEvent mouseEvent) {
        val stage = (Stage)titleBar.getScene().getWindow();
        if (mouseEvent.getClickCount() == 2){
            max(stage);
            stage.setFullScreen(true);
            return;
        }
        max(stage);
    }

    private void max(@NotNull Stage stage){
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }
}
