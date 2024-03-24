package org.visual.provider;

import jakarta.inject.Provider;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SceneProvider implements Provider<Scene> {
  @Override
  public Scene get() {
    return new Scene(new Pane());
  }
}
