package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import jakarta.inject.Named;
import java.util.List;
import java.util.prefs.Preferences;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.loader.EnvironmentVarsLoader;
import org.github.gestalt.config.loader.MapConfigLoader;
import org.github.gestalt.config.loader.PropertyLoader;
import org.github.gestalt.config.source.*;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.VisualModelDebugger;
import org.visual.model.shared.pojo.JavaFxProperty;

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
        val javafxClassPathSource = ClassPathConfigSourceBuilder.builder().setResource("javafx.properties").build();
        val systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build();
        val builder = new GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
        builder.addSources(List.of(classPathSource, environmentSource, systemSource, javafxClassPathSource));
        val gestalt = builder.build();
        gestalt.loadConfigs();
        return gestalt;
    }

    @SneakyThrows
    @Bean
    JavaFxProperty debuggerConfiguration(@NotNull @Named("VisualModelDebuggerGestalt") Gestalt gestalt) {
        return gestalt.getConfig("javafx", JavaFxProperty.class);
    }

    @Bean
    Preferences preferences() {
        return Preferences.userNodeForPackage(VisualModelDebugger.class);
    }
}
