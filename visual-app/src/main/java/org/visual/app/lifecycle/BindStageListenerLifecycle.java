package org.visual.app.lifecycle;

import jakarta.inject.Singleton;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.app.listener.StageWidthListener;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class BindStageListenerLifecycle implements StageLifecycle{
  private final StageWidthListener stageWidthListener;
  @Override
  public void beforeShown(@NotNull Stage stage) {
    stage.widthProperty().addListener(stageWidthListener);
  }
}
