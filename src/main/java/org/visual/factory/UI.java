package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.ViewConstant;
import org.visual.util.FxmlLoaderHelper;

@Factory
@Slf4j
@Lazy
public class UI {

  @Bean
  Parent mainLayout() {
    return FxmlLoaderHelper.load(ViewConstant.MAIN_LAYOUT);
  }

  @Bean
  Scene scene(Parent mainLayout) {
    return new Scene(mainLayout);
  }
}
