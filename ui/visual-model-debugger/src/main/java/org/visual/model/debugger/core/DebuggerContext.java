package org.visual.model.debugger.core;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DebuggerContext {

    INSTANCE;

    private final BeanScope beanScope = BeanScope.builder()
            .shutdownHook(true)
            .build();

    public <T> @NotNull T get(Class<T> clazz){
        return beanScope.get(clazz);
    }
}
