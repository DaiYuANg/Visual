package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.eventbus.EventBus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.di.DIContainer;
import org.visual.model.initializing.InitializeModule;
import org.visual.model.initializing.Initializer;
import org.visual.model.views.scene.WorkspaceScene;

import java.util.Set;
import java.util.concurrent.Executors;

@Slf4j
public class VisualModelIDE extends Application {

    @Inject
    private EventBus eventBus;

    @Override
    public void init() {
        log.info("Visual Model init");
        Platform.runLater(() -> DIContainer.INSTANCE.getInjector().injectMembers(this));
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
    }

    @Override
    public void start(@NotNull Stage stage) {
        stageInitialize(stage);
        val workspaceScene = DIContainer.INSTANCE.getInjector().getInstance(WorkspaceScene.class);
        workspaceScene.initialize();
        stage.setScene(workspaceScene.getScene());
        stage.show();
    }

    private void stageInitialize(Stage stage) {
        Guice.createInjector(new InitializeModule(stage))
                .getInstance(Key.get(new TypeLiteral<Set<Initializer>>() {
                }))
                .forEach(Initializer::initialize);
    }

    @SneakyThrows
    @Override
    public void stop() {
        eventBus.publish("shutdown", "now");
        TasksContext.INSTANCE.shutdown();
        log.info("visual model stop");
        System.exit(0);
    }
}
