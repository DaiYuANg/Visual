package org.visual.model.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class VisualModelUI extends Application {

    private final Scene rootScene;

    private final Stage rootStage;

    {
        val fxml = DIContainer.INSTANCE.load("MainLayout");
        rootScene = new Scene(fxml);
        rootStage = DIContainer.INSTANCE.getInjector().getInstance(Stage.class);
    }

    @Override
    public void init() {
        rootScene.getStylesheets().add("/help.css");
    }

    @Override
    public void start(@NotNull Stage stage) {
        rootStage.setScene(rootScene);
        log.info("start");
        rootStage.show();
    }
}
