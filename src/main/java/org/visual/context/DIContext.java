package org.visual.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.visual.api.ObjectDI;

@Getter
public enum DIContext implements ObjectDI {
  INSTANCE;

  private final BeanScope injector = BeanScope.builder().build();

  @Override
  public <T> @NotNull T get(Class<T> clazz) {
    return injector.get(clazz);
  }
}
