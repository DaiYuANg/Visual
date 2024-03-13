package org.visual.module;

import com.google.inject.AbstractModule;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.window.Tray;
import org.visual.provider.StageProvider;
import org.visual.provider.TrayProvider;

@Slf4j
public class VisualModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Stage.class).toProvider(StageProvider.class);
    bind(Tray.class).toProvider(TrayProvider.class);
  }
}
