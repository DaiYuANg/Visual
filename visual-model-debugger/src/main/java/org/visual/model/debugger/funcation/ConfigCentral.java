package org.visual.model.debugger.funcation;

import io.avaje.inject.Component;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import org.github.gestalt.config.Gestalt;

@Component
public class ConfigCentral {

    @Inject
    @Named("VisualModelDebuggerGestalt")
    Gestalt gestalt;

    @SneakyThrows
    @PostConstruct
    void init() {
        gestalt.loadConfigs();
    }

    void getConfig(String path) {
    }
}
