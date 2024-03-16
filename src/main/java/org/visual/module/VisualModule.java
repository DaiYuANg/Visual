package org.visual.module;

import com.google.inject.AbstractModule;
import jakarta.inject.Singleton;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.SearchPane;
import org.visual.component.window.Tray;
import org.visual.provider.SceneProvider;
import org.visual.provider.StageProvider;
import org.visual.provider.TrayProvider;
import org.visual.view.ShortcutRegistry;

@Slf4j
public class VisualModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Stage.class).toProvider(StageProvider.class).in(Singleton.class);
    bind(Tray.class).toProvider(TrayProvider.class).in(Singleton.class);
    bind(Scene.class).toProvider(SceneProvider.class).in(Singleton.class);
    bind(ShortcutRegistry.class).asEagerSingleton();
    bind(SearchPane.class).in(Singleton.class);
  }
}
