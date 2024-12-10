package org.visual.app.lifecycle;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Singleton
@Slf4j
@RequiredArgsConstructor
@Lazy
public class SetupSceneLifecycle implements StageLifecycle {
  private final Scene scene;

  @Override
  public void beforeShown(@NotNull Stage stage) {
    stage.setScene(scene);
  }
}
