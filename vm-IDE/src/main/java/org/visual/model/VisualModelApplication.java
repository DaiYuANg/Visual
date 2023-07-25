package org.visual.model;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.mvc.views.MainLayout;
import org.visual.model.setup.Setup;
import org.visual.model.setup.StageSetup;

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
