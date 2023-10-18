package org.visual.model.language.gui.ide.controllers;

import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.*;

import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.model.components.callbacks.ProjectCellFactory;
import org.visual.model.language.gui.ide.di.DIContainer;
import org.visual.model.language.gui.ide.functional.Project;
import org.visual.model.language.gui.ide.service.IProjectManager;

@Slf4j
public class CreateProjectController implements Initializable {

    @FXML
    public ListView<String> listView;

    @FXML
    public VBox vbox;

    @FXML
    public ListView<String> projectView;

    @FXML
    public Button saveButton;
    public ScrollPane projectScrollArea;

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

    private final IProjectManager projectManager;

    {
        this.projectManager = DIContainer.INSTANCE.getInjector().getInstance(IProjectManager.class);
    }

    private double startX;
    private double startWidth;
    private boolean dragging = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvent();
        projectView.setMinWidth(Control.USE_PREF_SIZE);
        projectView.setPrefWidth(Control.USE_COMPUTED_SIZE);
        projectView.setCellFactory(new ProjectCellFactory());
        projectView.getItems().addAll(projectManager.historyProjects().stream().map(Project::getName).toList());
    }

    private void bindEvent() {
        separator.setOnMouseEntered(event -> separator.setCursor(Cursor.H_RESIZE));
        separator.setOnMousePressed(this::onSeparatorPressed);
        separator.setOnMouseDragged(this::onSeparatorDragged);
        separator.setOnMouseReleased(this::onSeparatorReleased);
        saveButton.setOnMouseClicked(event -> eventBus.publish("changeScene", ""));
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
