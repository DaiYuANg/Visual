package org.visual.app.util;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.jthemedetecor.OsThemeDetector;
import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.app.context.DIContext;
import org.visual.app.listener.StageWidthListener;

import java.awt.*;

import static javafx.application.Application.setUserAgentStylesheet;

@Singleton
@Slf4j
@Lazy
@RequiredArgsConstructor
public class StageSetup {
  private final Toolkit toolkit = Toolkit.getDefaultToolkit();

  private final Dimension screenSize = toolkit.getScreenSize();

  private final Scene scene = DIContext.INSTANCE.get(Scene.class);

  private final StageWidthListener stageWidthListener;

  private final OsThemeDetector detector;

  public void setup(@NotNull Stage stage) {
    setUserAgentStylesheet(detectTheme());
    val width = screenSize.width * 0.8;
    val height = screenSize.height * 0.8;
    stage.setWidth(width);
    stage.setHeight(height);
    stage.setScene(scene);
    stage.widthProperty().addListener(stageWidthListener);
    stage.show();
    stage.requestFocus();
  }

  private @NotNull String detectTheme() {
    val isDark = detector.isDark();
    return isDark ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet();
  }
}
