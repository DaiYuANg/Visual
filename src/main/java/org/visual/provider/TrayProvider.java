package org.visual.provider;

import jakarta.inject.Provider;
import java.nio.file.Path;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.window.Tray;

@RequiredArgsConstructor
@Slf4j
public class TrayProvider implements Provider<Tray> {

  private final Stage rootStage;

  @Override
  public Tray get() {
    return new Tray(Path.of(""), rootStage);
  }
}
