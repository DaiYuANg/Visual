package org.visual.model.lifecycle.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.event.fx.handlers.OnCloseRequestHandler;
import org.visual.model.lifecycle.LifecycleManager;

@Slf4j
@Singleton
public class StageLifecycleManager implements LifecycleManager {

    @Inject
    private Stage stage;

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    @Override
    public void initialize() {
        log.atInfo().log("stage initializer executing");
        setOnClose();
        setView();
    }

    @Override
    public void stop() {

    }

    private void setOnClose() {
        stage.setOnCloseRequest(new OnCloseRequestHandler(stage));
    }

    private void setView() {
        stage.centerOnScreen();
        stage.setTitle(System.getProperty("application.name"));
        stage.setWidth(bounds.getWidth() * 0.8);
        stage.setHeight(bounds.getHeight() * 0.8);
    }
}
