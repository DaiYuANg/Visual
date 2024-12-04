package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import lombok.SneakyThrows;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.source.*;
import org.visual.model.GlobalConfig;
//import org.apitestify.config.VisualConfig;

@Factory
@Lazy
public class Config {

  @SneakyThrows
  @Bean
  ConfigSourcePackage classPathSource() {
    return ClassPathConfigSourceBuilder.builder().setResource("/default.properties").build();
  }

  @SneakyThrows
  @Bean
  ConfigSourcePackage environmentSource() {
    return EnvironmentConfigSourceBuilder.builder().setPrefix("MY_APP_CONFIG").build();
  }


  @SneakyThrows
  @Bean
  Gestalt gestalt() {
    return new GestaltBuilder()
      .addSource(ClassPathConfigSourceBuilder.builder().setResource("/default.properties").build())  // Load the default property files from resources.
//      .addSource(FileConfigSourceBuilder.builder().build())
//      .addSource(MapConfigSourceBuilder.builder().setCustomConfig(configs).build())
      .build();
  }

  @Bean
  GlobalConfig visualConfig() {
    return new GlobalConfig();
  }
}
