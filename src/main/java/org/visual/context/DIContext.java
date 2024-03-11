package org.visual.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.visual.config.ConfigModule;
import org.visual.module.RootModule;

@Getter
public enum DIContext {
  INSTANCE;
  private final Injector injector;

  DIContext() {
    injector = Guice.createInjector(new RootModule(), new ConfigModule());
  }

  public <T> @NotNull T get(Class<T> clazz) {
    return injector.getInstance(clazz);
  }
}
