package org.visual.provider;

import jakarta.inject.Provider;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SceneProvider implements Provider<Scene> {

  private final Scene instance = new Scene(new Parent() {});

  public SceneProvider() {
    log.info(instance.getRoot().toString());
  }

  @Override
  public Scene get() {
    return instance;
  }
}
