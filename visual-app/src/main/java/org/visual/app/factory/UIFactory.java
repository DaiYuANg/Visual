package org.visual.app.factory;

import com.jthemedetecor.OsThemeDetector;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.component.VisualScene;
import org.visual.app.constant.ViewConstant;
import org.visual.app.util.FXMLHelper;

@Factory
@Slf4j
@Lazy
@RequiredArgsConstructor
public class UIFactory {

  private final FXMLHelper fxmlHelper;

  @Bean
  VBox mainLayout() {
    return fxmlHelper.loadView(ViewConstant.MAIN_LAYOUT, VBox.class);
  }

  @Bean
  Scene scene(VBox mainLayout) {
    return new VisualScene(mainLayout);
  }

  @Bean
  OsThemeDetector themeDetector() {
    return OsThemeDetector.getDetector();
  }
}
