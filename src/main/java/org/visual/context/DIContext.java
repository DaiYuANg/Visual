package org.visual.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DIContext {
  INSTANCE;

  private final BeanScope injector = BeanScope.builder().shutdownHook(true).build();

  public <T> @NotNull T get(Class<T> clazz) {
    return injector.get(clazz);
  }
}
