package org.visual.database.factory;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Named;
import javafx.application.Application;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.component.display.VisualStage;
import org.visual.component.theme.OsThemeDetector;

@Factory
@Slf4j
public class UIFactory {

  @PostConstruct
  void initUI() {
    val theme =
        OsThemeDetector.getDetector().isDark()
            ? new PrimerDark().getUserAgentStylesheet()
            : new PrimerLight().getUserAgentStylesheet();
    Application.setUserAgentStylesheet(theme);
  }

  @Bean
  VisualStage rootStage() {
    val stage = new VisualStage();
    stage.initStyle(StageStyle.TRANSPARENT);
    return stage;
  }
}
