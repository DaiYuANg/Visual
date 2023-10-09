package org.visual.model.lifecycle.managers;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.DIContainer;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.views.scene.WorkspaceScene;

@Slf4j
@Singleton
public class SceneLifecycleManager implements LifecycleManager {

    private final Stage stage;

    @Inject
    public SceneLifecycleManager(Stage stage) {
        this.stage = stage;
        log.info("scene lifecycle executing:{}",stage);
    }

    @Override
    public void initialize() {
        val workspaceScene = DIContainer.INSTANCE.getInjector().getInstance(WorkspaceScene.class);
        workspaceScene.initialize();
        stage.setScene(workspaceScene.getScene());
    }

    @Override
    public void stop() {

    }
}
