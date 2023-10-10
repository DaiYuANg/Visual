package org.visual.model.controllers;

import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Separator;
import org.visual.model.components.controls.SpacedListCell;

@Slf4j
public class CreateProjectController implements Initializable {

    @FXML
    public ListView<String> listView;

    @FXML
    public VBox vbox;

    @FXML
    public ListView<HBox> projectView;

    @FXML
    public Button saveButton;

    @FXML
    private HBox hbox;

    @FXML
    private Pane leftPane;

    @FXML
    private Separator separator;

    @FXML
    private Pane rightPane;

    @Inject
    private EventBus eventBus;

    private double startX;
    private double startWidth;
    private boolean dragging = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        separator.setOnMouseEntered( event -> {
            log.info("in");
            separator.setCursor(Cursor.H_RESIZE);
        });
        separator.setOnMousePressed(this::onSeparatorPressed);
        separator.setOnMouseDragged(this::onSeparatorDragged);
        separator.setOnMouseReleased(this::onSeparatorReleased);
        saveButton.setOnMouseClicked(event -> eventBus.publish("clickSetting",""));
    }

    private void onSeparatorPressed(@NotNull MouseEvent event) {
        startX = event.getSceneX();
        startWidth = leftPane.getWidth();
        dragging = true;
    }

    private void onSeparatorDragged(MouseEvent event) {
        if (dragging) {
            double offsetX = event.getSceneX() - startX;
            double newWidth = startWidth + offsetX;

            if (newWidth >= 5 && newWidth <= hbox.getWidth() - 5) {
                leftPane.setPrefWidth(newWidth);
            }
        }
    }

    private void onSeparatorReleased(MouseEvent event) {
//        dragging = false;
    }
}
