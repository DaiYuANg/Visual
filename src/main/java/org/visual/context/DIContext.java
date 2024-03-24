package org.visual.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.visual.api.ObjectDI;
import org.visual.module.VisualModule;

@Getter
public enum DIContext implements ObjectDI {
  INSTANCE;

  private final Injector injector = Guice.createInjector(new VisualModule());

  @Override
  public <T> @NotNull T get(Class<T> clazz) {
    return injector.getInstance(clazz);
  }
}
