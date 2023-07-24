package org.visual.model.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.base.Controller;
import org.visual.model.contexts.AsyncContext;

@Slf4j
public class SettingsController implements Controller {

    public VBox settingList;

    public SplitPane splitPane;

    private final Double defaultSplitDriver = 0.2;


    @Override
    public void initialize() {
        log.info("settings initialize");
//        AsyncContext.ASYNC.run(this::lockSplitPane);
        lockSplitPane();
    }

    private void lockSplitPane() {
        log.info("lock split panel");
        splitPane.setDividerPositions(defaultSplitDriver);
        splitPane.getDividers()
                .forEach(d -> d.positionProperty().addListener((observable, oldValue, newValue) -> {
                    splitPane.setDividerPosition(0,defaultSplitDriver);
                }));
    }
}
