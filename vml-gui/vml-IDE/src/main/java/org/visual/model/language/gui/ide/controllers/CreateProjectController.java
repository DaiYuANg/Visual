package org.visual.model.language.gui.ide.controllers;

import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.language.gui.ide.di.DIContainer;
import org.visual.model.language.gui.ide.service.IProjectManager;

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

    private IProjectManager projectManager;

    private double startX;
    private double startWidth;
    private boolean dragging = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvent();
        this.projectManager = DIContainer.INSTANCE.getInjector().getInstance(IProjectManager.class);
    }

    private void bindEvent() {
        separator.setOnMouseEntered(event -> separator.setCursor(Cursor.H_RESIZE));
        separator.setOnMousePressed(this::onSeparatorPressed);
        separator.setOnMouseDragged(this::onSeparatorDragged);
        separator.setOnMouseReleased(this::onSeparatorReleased);
        saveButton.setOnMouseClicked(event -> eventBus.publish("clickSetting", ""));
    }

    private void onSeparatorPressed(@NotNull MouseEvent event) {
        startX = event.getSceneX();
        startWidth = leftPane.getWidth();
        dragging = true;
    }

    private void onSeparatorDragged(MouseEvent event) {
        if (!dragging) {
            return;
        }
        double offsetX = event.getSceneX() - startX;
        double newWidth = startWidth + offsetX;
        if (!(newWidth >= 20) || !(newWidth <= hbox.getWidth() - 20)) {
            return;
        }
        leftPane.setPrefWidth(newWidth);
    }

    private void onSeparatorReleased(MouseEvent event) {
        dragging = false;
    }
}