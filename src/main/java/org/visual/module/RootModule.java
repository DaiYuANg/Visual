package org.visual.module;

import com.google.inject.AbstractModule;
import java.util.concurrent.Executor;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.collaborative.CollaborativeModule;
import org.visual.component.window.Tray;
import org.visual.local.store.VisualLocalStoreModule;
import org.visual.provider.ExecutorProvider;
import org.visual.provider.StageProvider;
import org.visual.provider.TrayProvider;

@Slf4j
public class RootModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new VisualLocalStoreModule());
    install(new CollaborativeModule());
    bind(Executor.class).toProvider(ExecutorProvider.class);
    bind(Stage.class).toProvider(StageProvider.class);
    bind(Tray.class).toProvider(TrayProvider.class);
  }
}
