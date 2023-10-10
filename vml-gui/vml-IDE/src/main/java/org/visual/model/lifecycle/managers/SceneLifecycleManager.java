package org.visual.model.lifecycle.managers;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.constants.EventBuses;
import org.visual.model.di.DIContainer;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.service.IProjectManager;
import org.visual.model.views.scene.CreateProjectScene;
import org.visual.model.views.scene.WorkspaceScene;

@Slf4j
@Singleton
public class SceneLifecycleManager implements LifecycleManager {

    private final Stage stage;

    private final EventBus eventBus;

    private final IProjectManager projectManager;

    @Inject
    public SceneLifecycleManager(Stage stage) {
        this.stage = stage;
        eventBus = DIContainer.INSTANCE.getInjector().getInstance(EventBus.class);
        projectManager = DIContainer.INSTANCE.getInjector().getInstance(IProjectManager.class);
        log.info("scene lifecycle executing:{}", stage);
        listenSwitchScene();
    }

    @SneakyThrows
    @Override
    public void initialize() {
        projectManager.initialize();
        val createProject = DIContainer.INSTANCE.getInjector().getInstance(CreateProjectScene.class);
        stage.setScene(createProject.initializeScene());
    }

    private void listenSwitchScene() {
        eventBus.consumer(EventBuses.STAGE_SWITCH_SCENE.toString(),
                (Handler<Message<Scene>>) event -> Platform.runLater(() -> stage.setScene(event.body())));
    }

    @Override
    public void stop() {
        projectManager.saveProjects();
    }
}
