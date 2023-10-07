package org.visual.model.initializing;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.handlers.OnCloseRequestHandler;

@Slf4j
@Singleton
public class StageInitializer implements Initializer {

    @Inject
    private Stage stage;

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    @Override
    public void initialize() {
        log.atInfo().log("stage initializer executing");
        setOnClose();
        setView();
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
