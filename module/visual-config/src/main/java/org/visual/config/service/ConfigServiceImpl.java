package org.visual.config.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.Gestalt;
import org.jetbrains.annotations.NotNull;
import org.visual.config.api.ConfigService;
import org.visual.config.model.UIConfiguration;

@Slf4j
@Singleton
public class ConfigServiceImpl implements ConfigService {

  private final Gestalt gestalt;

  @SneakyThrows
  @Inject
  ConfigServiceImpl(@NotNull Gestalt gestalt) {
    this.gestalt = gestalt;
    gestalt.loadConfigs();
  }

  @SneakyThrows
  @Override
  public UIConfiguration loadUIConfig() {
    return gestalt.getConfig("ui", UIConfiguration.class);
  }
}
