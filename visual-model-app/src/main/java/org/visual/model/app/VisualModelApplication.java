package org.visual.model.app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.visual.model.ui.util.StageInspector.inspect;

@Slf4j
@NoArgsConstructor
public class VisualModelApplication extends Application {
    private final Parent rootFxml = DIContainer.INSTANCE.load("MainLayout");

    private final Scene rootScene = new Scene(rootFxml);

    private final Stage rootStage = DIContainer.INSTANCE.getInjector().getInstance(Stage.class);

    @Override
    public void init() {
        rootScene.getStylesheets().addAll("/help.css", "/theme.css");
        rootScene.setFill(Color.TRANSPARENT);
    }

    @Override
    public void start(Stage stage) {
        rootStage.setScene(rootScene);
        log.info("start");
        rootStage.show();
        inspect(rootStage);
    }

    public static void main(String[] args) {
        Application.launch(VisualModelApplication.class, args);
    }
}
