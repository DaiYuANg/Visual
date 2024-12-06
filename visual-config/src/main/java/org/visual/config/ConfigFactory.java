package org.visual.config;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.exceptions.GestaltException;

@Factory
@Slf4j
public class ConfigFactory {
  private final Gestalt gestalt;

  public ConfigFactory(Gestalt gestalt) throws GestaltException {
    this.gestalt = gestalt;
    gestalt.debugPrint();
    gestalt.loadConfigs();
    log.atDebug().log(gestalt.debugPrint());
  }

  @Bean
  GlobalConfig visualConfig() {
    return new GlobalConfig();
  }

  @SneakyThrows
  @Bean
  UIConfig uiConfig() {
    return gestalt.getConfig("visual.ui", UIConfig.class);
  }
}
