package org.visual.model;

import atlantafx.base.theme.CupertinoDark;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.views.MainScene;
import org.visual.model.initializing.StageSetup;

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
//                final OsThemeDetector detector = OsThemeDetector.getDetector();
        //        detector.registerListener(isDark -> {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
//        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
//        Platform.runLater(() -> {
//            if (false) {
//                Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
//            } else {
//                Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
//            }
//        });
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
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
