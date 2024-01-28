/* (C)2024*/
package org.visual.designer.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import jakarta.inject.Named;
import java.util.List;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.*;
import org.github.gestalt.config.toml.TomlLoader;

@Factory
public class ConfigFactory {

  @SneakyThrows
  @Bean
  @Named("VisualModelAppGestalt")
  Gestalt gestalt() {
    val configLoaders =
        List.of(
            new EnvironmentVarsLoader(),
            new TomlLoader(),
            new PropertyLoader(),
            new MapConfigLoader());
    val environmentSource =
        EnvironmentConfigSourceBuilder.builder()
            .setPrefix("VISUAL_MODEL")
            .setFailOnErrors(false)
            .build();
    String defaultConfig = "visual.model.properties";
    //        val mapSource = MapConfigSourceBuilder.builder()
    //                .setCustomConfig(ManifestReader.loadManifestStrings())
    //                .build();
    val classPathSource = ClassPathConfigSourceBuilder.builder().setResource(defaultConfig).build();
    val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build();
    val builder = new GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
    builder.addSources(List.of(classPathSource, environmentSource, systemSource));
    return builder.build();
  }
}
