package org.visual.module;

import com.google.inject.AbstractModule;
import java.util.concurrent.Executor;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.visual.provider.ExecutorProvider;
import org.visual.provider.SceneProvider;
import org.visual.provider.StageProvider;

public class VisualModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Executor.class).toProvider(ExecutorProvider.class);
    bind(Stage.class).toProvider(StageProvider.class);
    bind(Scene.class).toProvider(SceneProvider.class);
  }
}
