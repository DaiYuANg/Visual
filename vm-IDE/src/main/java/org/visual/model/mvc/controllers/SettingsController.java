package org.visual.model.mvc.controllers;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.mvc.base.Controller;

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
