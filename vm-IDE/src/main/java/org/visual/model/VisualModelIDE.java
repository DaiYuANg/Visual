package org.visual.model;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.contexts.UIContext;
import org.visual.model.mvc.views.MainLayout;

@Slf4j
public class VisualModelIDE extends Application {
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(MainLayout.INSTANCE.getScene());
        UIContext.UICONTEXT.initializeSize();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        TasksContext.ASYNC.shutdown();
        log.info("visual model stop");
    }
}
