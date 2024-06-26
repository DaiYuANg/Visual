package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import lombok.SneakyThrows;
import org.visual.config.VisualConfig;

@Factory
@Lazy
public class ConfigFactory {

  @SneakyThrows
  @Bean
  VisualConfig visualConfig() {
    return new VisualConfig();
  }
}
