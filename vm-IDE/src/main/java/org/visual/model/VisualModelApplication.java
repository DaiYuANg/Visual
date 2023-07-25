package org.visual.model;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.ProjectContext;
import org.visual.model.initializing.EventSetup;
import org.visual.model.initializing.StageSetup;
import org.visual.model.mvc.views.MainScene;

@Slf4j
public class VisualModelApplication extends Application {
    private MainScene mainScene;

    @Override
    public void init() {
        loadApplication();
        log.info("visual modeling starting");
        logging();
        this.mainScene = MainScene.MAIN_SCENE;
    }

    private void loadApplication() {
        log.info(ProjectContext.PROJECT.toString());
        log.info(EventSetup.EVENT_SETUP.toString());
//                final OsThemeDetector detector = OsThemeDetector.getDetector();
        //        detector.registerListener(isDark -> {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                val alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
            }
        });
        new StageSetup(stage, mainScene);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        log.info("visual modeling stop");
    }
}
