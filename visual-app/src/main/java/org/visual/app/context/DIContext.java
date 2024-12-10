package org.visual.app.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

  @Override
  public <T> @NotNull List<T> getAll(Class<T> clazz) {
    return injector.list(clazz);
  }
}
