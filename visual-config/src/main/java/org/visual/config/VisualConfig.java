package org.visual.config;

import io.vavr.control.Try;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;
import org.visual.config.constant.ConfigConstant;

@Slf4j
public class VisualConfig {

  private final Gestalt gestalt;

  @SneakyThrows
  public VisualConfig() {
    Try.of(ConfigConstant.HOME_CONFIG::toFile)
        .mapTry(
            file -> {
              if (Boolean.FALSE.equals(file.exists())) {
                log.atInfo().log("File not exists:{}", file.getAbsolutePath());
                return file.createNewFile();
              }
              return true;
            })
        .getOrElseThrow(() -> new RuntimeException("Home Config file does not exist"));
    val gestalt =
        new GestaltBuilder()
            .addSource(
                ClassPathConfigSourceBuilder.builder().setResource("visual.properties").build())
            .addSource(SystemPropertiesConfigSourceBuilder.builder().build())
            .addSource(EnvironmentConfigSourceBuilder.builder().build())
            .addConfigLoader(new TomlLoader())
            .addConfigLoader(new PropertyLoader())
            .addConfigLoader(new EnvironmentVarsLoader())
            //      .setDefaultTags(Tags.profile("dev"))
            .build();
    this.gestalt = gestalt;
    gestalt.loadConfigs();
  }
}
