package org.visual.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.entity.GestaltModuleConfig;
import org.github.gestalt.config.lexer.PathLexer;
import org.github.gestalt.config.loader.EnvironmentVarsLoaderModuleConfigBuilder;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.ConfigSourcePackage;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlModuleConfigBuilder;
import org.github.gestalt.config.yaml.YamlModuleConfigBuilder;
import org.visual.VisualApplication;

import java.util.Set;
import java.util.prefs.Preferences;

@Factory
@Lazy
public class Config {

  @SneakyThrows
  @Bean
  @Named("classPathSource")
  ConfigSourcePackage classPathSource() {
    return ClassPathConfigSourceBuilder.builder().setResource("/application.yaml").build();
  }

  @SneakyThrows
  @Bean
  @Named("environmentSource")
  ConfigSourcePackage environmentSource() {
    return EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL").build();
  }

  @SneakyThrows
  @Bean
  @Named("SystemPropertiesSource")
  ConfigSourcePackage SystemPropertiesSource() {
    return SystemPropertiesConfigSourceBuilder.builder().build();
  }

  @Bean
  @Named("envLoader")
  GestaltModuleConfig envLoader() {
    return EnvironmentVarsLoaderModuleConfigBuilder
      .builder()
      .setLexer(new PathLexer("_"))
      .build();
  }

  @Bean
  @Named("yamlLoader")
  GestaltModuleConfig yamlLoader() {
    return YamlModuleConfigBuilder.builder()
      .build();
  }

  @Bean
  @Named("tomlLoader")
  GestaltModuleConfig tomlLoader() {
    return TomlModuleConfigBuilder.builder()
      .build();
  }

  @SneakyThrows
  @Bean
  Gestalt gestalt(
    Set<ConfigSourcePackage> sources,
    Set<GestaltModuleConfig> moduleConfigs
  ) {
    val builder = new GestaltBuilder()
      .setValidationEnabled(true);
    sources.forEach(builder::addSource);
    moduleConfigs.forEach(builder::addModuleConfig);
    return builder.build();
  }

  @Bean
  Preferences preferences() {
    return Preferences.userNodeForPackage(VisualApplication.class);
  }
}
