package org.visual.provider;

import jakarta.inject.Provider;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.loader.ConfigLoader;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.*;
import org.github.gestalt.config.toml.TomlLoader;

@Slf4j
public class ConfigProvider implements Provider<Gestalt> {

  private final String defaultConfig = "visual.model.properties";

  private final List<ConfigLoader> configLoaders =
      List.of(
          new EnvironmentVarsLoader(),
          new TomlLoader(),
          new PropertyLoader(),
          new MapConfigLoader());

  private final List<ConfigSourcePackage> configSources =
      List.of(
          EnvironmentConfigSourceBuilder.builder()
              .setPrefix("VISUAL_MODEL")
              .setFailOnErrors(false)
              .build(),
          ClassPathConfigSourceBuilder.builder().setResource(defaultConfig).build(),
          SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build());

  public ConfigProvider() throws GestaltException {}

  @SneakyThrows
  @Override
  public Gestalt get() {
    val builder = new GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
    builder.addSources(configSources);
    return builder.build();
  }
}
