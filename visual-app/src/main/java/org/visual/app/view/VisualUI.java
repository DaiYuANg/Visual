/* (C)2024*/
package org.visual.app.view;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.jthemedetecor.OsThemeDetector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.config.UIConfig;

import java.awt.*;

import static javafx.application.Platform.setImplicitExit;
import static org.visual.app.context.DIContext.INSTANCE;

@Slf4j
public class VisualUI extends Application {
  private final OsThemeDetector detector = INSTANCE.get(OsThemeDetector.class);
  private final UIConfig uiConfig = INSTANCE.get(UIConfig.class);
  private final Toolkit toolkit = Toolkit.getDefaultToolkit();

  @Override
  public void init() {
    log.info("UI init");
    setImplicitExit(false);
    Application.setUserAgentStylesheet(detectTheme());
    log.atInfo().log("UI Config:{}", uiConfig);
  }

  private @NotNull String detectTheme() {
    return detector.isDark() ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet();
  }

  @SneakyThrows
  @Override
  public void start(@NotNull Stage stage) {
    log.atInfo().log("UI Started");
    // 获取默认的图形工具包
    val screenSize = toolkit.getScreenSize();
    val width = screenSize.width * 0.8;
    val height = screenSize.height * 0.8;
    val scene = INSTANCE.get(Scene.class);
    stage.setScene(scene);
    stage.setWidth(width);
    stage.setHeight(height);
    stage.show();
    stage.requestFocus();
  }
}
