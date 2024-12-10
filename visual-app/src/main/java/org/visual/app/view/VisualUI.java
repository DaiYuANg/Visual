/* (C)2024*/
package org.visual.app.view;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.app.lifecycle.StageLifecycle;
import org.visual.app.lifecycle.ViewLifecycle;

import java.util.List;

import static org.visual.app.context.DIContext.INSTANCE;

@Slf4j
public class VisualUI extends Application {
  private final List<ViewLifecycle> viewLifecycles = INSTANCE.getAll(ViewLifecycle.class);

  private final List<StageLifecycle> stageLifecycles = INSTANCE.getAll(StageLifecycle.class);

  @Override
  public void init() {
    log.info("UI init");
    viewLifecycles.forEach(ViewLifecycle::onInit);
  }

  @Override
  public void start(@NotNull Stage stage) {
    stageLifecycles.forEach(lifecycle -> lifecycle.beforeShown(stage));
    stage.show();
    stage.requestFocus();
  }

  @Override
  public void stop() throws Exception {
    viewLifecycles.forEach(ViewLifecycle::onStop);
    System.exit(0);
  }
}
