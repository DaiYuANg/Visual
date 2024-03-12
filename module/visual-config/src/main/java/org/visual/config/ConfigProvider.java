package org.visual.config;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.json.JsonLoader;
import org.github.gestalt.config.loader.ConfigLoader;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.ConfigSourcePackage;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;
import org.github.gestalt.config.yaml.YamlLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;

@Slf4j
@Singleton
public class ConfigProvider implements Provider<Gestalt> {

  @Contract(" -> new")
  private @Unmodifiable List<ConfigLoader> buildConfigLoaders() {
    return List.of(
        new EnvironmentVarsLoader(),
        new TomlLoader(),
        new PropertyLoader(),
        new MapConfigLoader(),
        new YamlLoader(),
        new JsonLoader());
  }

  @Contract(" -> new")
  @SneakyThrows
  private @Unmodifiable List<ConfigSourcePackage> buildConfigSources() {
    return List.of(
        EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL").setFailOnErrors(false).build(),
        ClassPathConfigSourceBuilder.builder().setResource("visual.model.properties").build(),
        SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build()
        //      FileConfigSourceBuilder.builder()
        //        .setPath(Path.of(BaseDirectories.get().configDir, "visual.toml"))
        //        .build(),
        //      FileConfigSourceBuilder.builder()
        //        .setPath(Path.of(BaseDirectories.get().configDir, "visual.json"))
        //        .build(),
        //      FileConfigSourceBuilder.builder()
        //        .setPath(Path.of(BaseDirectories.get().configDir, "visual.yaml"))
        //        .build()
        );
  }

  private Gestalt buildGestalt() throws GestaltException {
    val configSources = buildConfigSources();
    val configLoaders = buildConfigLoaders();
    return new GestaltBuilder()
        .useCacheDecorator(true)
        .addConfigLoaders(configLoaders)
        .addSources(configSources)
        .useCacheDecorator(true)
        .build();
  }

  @SneakyThrows
  @Override
  public Gestalt get() {
    return buildGestalt();
  }
}
