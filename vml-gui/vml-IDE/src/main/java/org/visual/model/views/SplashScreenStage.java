package org.visual.model.views;

import javafx.application.Preloader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SplashScreenStage extends Preloader {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        super.handleStateChangeNotification(info);
    }

    @Override
    public boolean handleErrorNotification(ErrorNotification info) {
        return super.handleErrorNotification(info);
    }
}
