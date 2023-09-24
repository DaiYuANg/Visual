package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.vertx.core.Vertx;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.contexts.UIContext;
import org.visual.model.modules.AppModule;
import org.visual.model.modules.ITest;
import org.visual.model.modules.Test;
import org.visual.model.mvc.views.MainLayout;


@Slf4j
public class VisualModelIDE extends Application {

    @Inject
    ITest test;

    @Override
    public void init() {
        val i = Guice.createInjector(new AppModule());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VisualModelIDE.class.getName()));
        loader.setControllerFactory(i::getInstance);
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
