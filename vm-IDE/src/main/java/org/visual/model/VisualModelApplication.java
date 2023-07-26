package org.visual.model;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.UIContext;
import org.visual.model.mvc.views.MainLayout;

import java.util.Optional;

@Slf4j
public class VisualModelApplication extends Application {
    @Override
    public void init() {
        loadApplication();
        logging();
    }

    private void loadApplication() {

    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
        UIContext.UICONTEXT.setStage(stage);
        stage.setScene(MainLayout.INSTANCE.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        log.info("visual model stop");
    }
}
