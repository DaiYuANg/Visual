package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import javafx.scene.Scene;
import lombok.extern.slf4j.Slf4j;
import org.visual.view.MainLayout;

@Factory
@Slf4j
@Lazy
public class UIFactory {

  @Bean
  Scene scene(MainLayout mainLayout) {
    return new Scene(mainLayout);
  }
}
