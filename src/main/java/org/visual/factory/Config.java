package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.ConfigSourcePackage;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.visual.model.GlobalConfig;

import java.util.Set;

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
    return EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL").build();
  }

  @SneakyThrows
  @Bean
  ConfigSourcePackage SystemPropertiesSource() {
    return SystemPropertiesConfigSourceBuilder.builder().build();
  }

  @SneakyThrows
  @Bean
  Gestalt gestalt(Set<ConfigSourcePackage> sources) {
    val builder = new GestaltBuilder();
    sources.forEach(builder::addSource);
    return builder.build();
  }

  @Bean
  GlobalConfig visualConfig() {
    return new GlobalConfig();
  }
}
