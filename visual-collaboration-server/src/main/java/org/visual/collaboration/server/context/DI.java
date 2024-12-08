package org.visual.collaboration.server.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DI {
  INSTANCE;

  private final BeanScope beanScope = BeanScope.builder().build();

  public <T> @NotNull T get(Class<T> clazz) {
    return beanScope.get(clazz);
  }
}
