package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.exceptions.GestaltException;
import org.visual.model.GlobalConfig;
import org.visual.model.UIConfig;

@Factory
@Slf4j
@Lazy
public class Conf {

  private final Gestalt gestalt;

  public Conf(Gestalt gestalt) throws GestaltException {
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
