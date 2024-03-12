package org.visual.provider;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.nio.file.Path;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.window.Tray;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@Singleton
public class TrayProvider implements Provider<Tray> {

  private final Stage rootStage;

  @Override
  public Tray get() {
    return new Tray(Path.of(""), rootStage);
  }
}
