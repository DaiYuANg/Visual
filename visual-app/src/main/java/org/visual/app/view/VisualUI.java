/* (C)2024*/
package org.visual.app.view;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.app.repository.SavedStateRepository;
import org.visual.app.util.StageSetup;

import static org.visual.app.context.DIContext.INSTANCE;

@Slf4j
public class VisualUI extends Application {
  private final StageSetup stageSetup = INSTANCE.get(StageSetup.class);
  private final SavedStateRepository stateRepository = INSTANCE.get(SavedStateRepository.class);

  @Override
  public void init() {
    log.info("UI init");
  }

  @SneakyThrows
  @Override
  public void start(@NotNull Stage stage) {
    stageSetup.setup(stage);
  }

  @Override
  public void stop() throws Exception {
    System.exit(0);
  }
}
