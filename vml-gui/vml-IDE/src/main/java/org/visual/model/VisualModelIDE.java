package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.google.inject.Inject;
import io.vertx.core.eventbus.EventBus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.di.DIContainer;
import org.visual.model.di.modules.InitializingModule;
import org.visual.model.initializing.IStageInitializer;
import org.visual.model.services.IWorkspaceService;

@Slf4j
public class VisualModelIDE extends Application {

    @Inject
    private EventBus eventBus;

    @Override
    public void init() {
        log.info("Visual Model init");
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
        Platform.runLater(() -> DIContainer.INSTANCE.getInjector().injectMembers(this));
    }

    @Override
    public void start(@NotNull Stage stage) {
        log.info("init stage {}", stage);
        stage.show();
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
