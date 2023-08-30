package org.visual.model;

import app.supernaut.fx.ApplicationDelegate;
import app.supernaut.fx.FxLauncher;
import atlantafx.base.theme.PrimerLight;
import jakarta.inject.Singleton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.contexts.UIContext;
import org.visual.model.modules.Test;
import org.visual.model.mvc.views.MainLayout;


@Slf4j
@Singleton
public class VisualModelIDE implements ApplicationDelegate {

    @Override
    public void init() {
        loadApplication();
        logging();
    }

    private void loadApplication() {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        // Application.setUserAgentStylesheet(new
        // PrimerDark().getUserAgentStylesheet());
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        var label = new Label("Hello, " + "123" + "!");
        var scene = new Scene(new StackPane(label), 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SupernautFX Minimal App");
        primaryStage.show();
//        UIContext.UICONTEXT.setStage(stage);
//        stage.setScene(MainLayout.INSTANCE.getScene());
//        UIContext.UICONTEXT.initializeSize();
//        stage.show();
    }

    public static void main(String[] args) {
        FxLauncher.find().launch(args, VisualModelIDE.class);
    }

    @Override
    public void stop() {
        TasksContext.ASYNC.shutdown();
        log.info("visual model stop");
    }
}
