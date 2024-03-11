package org.visual.config;

import com.google.inject.AbstractModule;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.guice.GestaltModule;
import org.github.gestalt.config.json.JsonLoader;
import org.github.gestalt.config.loader.ConfigLoader;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.*;
import org.github.gestalt.config.toml.TomlLoader;
import org.github.gestalt.config.yaml.YamlLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.visual.config.api.ConfigService;
import org.visual.config.service.ConfigServiceImpl;

@Slf4j
public class ConfigModule extends AbstractModule {

  @SneakyThrows
  @Override
  protected void configure() {
    val gestalt = buildGestalt();
    install(new GestaltModule(gestalt));
    bind(ConfigService.class).to(ConfigServiceImpl.class);
  }

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
}
