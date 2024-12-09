package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.Gestalt;
import org.visual.collaboration.server.model.HttpConfig;

@Factory
@Slf4j
public class ConfigFactory {

  private final Gestalt gestalt;

  @SneakyThrows
  public ConfigFactory(Gestalt gestalt) {
    gestalt.debugPrint();
    gestalt.loadConfigs();
    log.atDebug().log(gestalt.debugPrint());
    this.gestalt = gestalt;
  }

  @SneakyThrows
  @Bean
  HttpConfig httpConfig() {
    return gestalt.getConfig("visual.http", HttpConfig.class);
  }
}
