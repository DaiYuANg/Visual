package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.google.inject.Guice;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.contexts.UIContext;
import org.visual.model.modules.RootModule;
import org.visual.model.views.MainLayout;


@Slf4j
public class VisualModelIDE extends Application {

    @Override
    public void init() {
        val i = Guice.createInjector(new RootModule());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLayout.fxml"));
        loadApplication();
        logging();
    }

    private void loadApplication() {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
        UIContext.UICONTEXT.setStage(stage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);
        stage.setScene(MainLayout.INSTANCE.getScene());
        UIContext.UICONTEXT.initializeSize();
        stage.show();
    }

    @Override
    public void stop() {
        TasksContext.ASYNC.shutdown();
        log.info("visual model stop");
    }
}
