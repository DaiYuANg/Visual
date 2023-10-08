package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.google.inject.*;
import io.vertx.core.eventbus.EventBus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.AsyncContext;
import org.visual.model.di.DIContainer;
import org.visual.model.lifecycle.LifeCycileManagerModule;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.views.scene.WorkspaceScene;

import java.util.Set;

@Slf4j
public class VisualModelIDE extends Application {

    @Inject
    private EventBus eventBus;

    private final TypeLiteral<Set<LifecycleManager>> typeLiteral = new TypeLiteral<>() {
    };

    private Injector lifeCycileManagerInjector;

    private Set<LifecycleManager> lifecycleManagers;

    @Override
    public void init() {
        log.info("Visual Model init");
        Platform.runLater(() -> DIContainer.INSTANCE.getInjector().injectMembers(this));
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
    }

    @Override
    public void start(@NotNull Stage stage) {
//        DIContainer.INSTANCE.getInjector().injectMembers(this);
        stageInitialize(stage);
        val workspaceScene = DIContainer.INSTANCE.getInjector().getInstance(WorkspaceScene.class);
        workspaceScene.initialize();
        stage.setScene(workspaceScene.getScene());
        stage.show();
    }

    private void stageInitialize(Stage stage) {
        lifeCycileManagerInjector = Guice.createInjector(new LifeCycileManagerModule(stage));
        lifecycleManagers = lifeCycileManagerInjector.getInstance(Key.get(typeLiteral));
        lifecycleManagers.forEach(LifecycleManager::initialize);
    }

    @SneakyThrows
    @Override
    public void stop() {
        lifecycleManagers.forEach(LifecycleManager::stop);
        AsyncContext.INSTANCE.shutdown();
        log.info("visual model stop");
        System.exit(0);
    }
}
