package org.visual.model.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.github.gestalt.config.toml.TomlLoader;

import java.util.List;

@Factory
public class ConfigFactory {

    @SneakyThrows
    @Bean
    @Named("VisualModelAppGestalt")
    Gestalt gestalt() {
        val configLoaders = List.of(new EnvironmentVarsLoader(), new TomlLoader(), new PropertyLoader(), new MapConfigLoader());
        val environmentSource = EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL_MODEL")
                .setFailOnErrors(false).build();
        String defaultConfig = "visual.model.properties";
        val classPathSource = ClassPathConfigSourceBuilder.builder().setResource(defaultConfig).build();
        val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build();
        val builder = new GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
        builder.addSources(List.of(classPathSource, environmentSource, systemSource));
        return builder.build();
    }
}
