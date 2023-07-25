package org.visual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

@AllArgsConstructor
public enum Scanner {
    INSTANCE(new Reflections(
            new ConfigurationBuilder()
                    .forPackages(VisualModelApplication.class.getPackageName())
    ));

    @Getter
    private final Reflections scanner;
}
