package org.visual.app.lifecycle;

import jakarta.inject.Singleton;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@Singleton
@Slf4j
public class SetupStageWindowSize implements StageLifecycle{
  private final Toolkit toolkit = Toolkit.getDefaultToolkit();

  private final Dimension screenSize = toolkit.getScreenSize();

  @Override
  public void beforeShown(@NotNull Stage stage) {
    val width = screenSize.width * 0.8;
    val height = screenSize.height * 0.8;
    stage.setWidth(width);
    stage.setHeight(height);
  }
}
