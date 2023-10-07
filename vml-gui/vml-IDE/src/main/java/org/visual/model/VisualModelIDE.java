package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
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
import org.visual.model.di.modules.InitializeModule;
import org.visual.model.initializing.Initializer;
import org.visual.model.views.scene.CreateProjectScene;
import org.visual.model.views.scene.WorkspaceScene;

import java.util.Set;

@Slf4j
public class VisualModelIDE extends Application {

    @Inject
    private EventBus eventBus;

    @Inject
    private WorkspaceScene workspaceScene;

    @Override
    public void init() {
        log.info("Visual Model init");
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
        Platform.runLater(() -> DIContainer.INSTANCE.getInjector().injectMembers(this));
    }

    @Override
    public void start(@NotNull Stage stage) {
        System.err.println(DIContainer.INSTANCE);
        val initializerInjector = DIContainer.INSTANCE.getInjector().createChildInjector(new InitializeModule(stage));
        val implementations = initializerInjector.getInstance(Key.get(new TypeLiteral<Set<Initializer>>() {
        }));
        implementations.parallelStream().forEach(Initializer::initialize);
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
