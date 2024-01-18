package org.visual.model.debugger.factory;

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
import org.github.gestalt.config.source.*;

import java.util.List;

@Factory
public class ConfigFactory {

    @SneakyThrows
    @Bean
    @Named("VisualModelDebuggerGestalt")
    Gestalt gestalt() {
        val configLoaders = List.of(new EnvironmentVarsLoader(), new PropertyLoader(), new MapConfigLoader());
        val environmentSource = EnvironmentConfigSourceBuilder.builder().setPrefix("VISUAL_MODEL")
                .setFailOnErrors(false).build();
        val classPathSource = ClassPathConfigSourceBuilder.builder().setResource("visual.model.debugger.properties").build();
        val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build();
        val builder = new GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
        builder.addSources(List.of(classPathSource, environmentSource, systemSource));
        return builder.build();
    }
}
