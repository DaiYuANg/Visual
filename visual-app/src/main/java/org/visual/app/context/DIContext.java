package org.visual.app.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Getter
@Slf4j
public enum DIContext implements ObjectDI {
  INSTANCE;

  private final BeanScope injector;

  DIContext() {
    this.injector = BeanScope.builder().shutdownHook(true).build();
  }

  @Override
  public <T> @NotNull T get(Class<T> clazz) {
    return injector.get(clazz);
  }
}
