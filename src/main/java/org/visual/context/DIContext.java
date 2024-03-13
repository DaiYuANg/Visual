package org.visual.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.visual.local.store.LocalStoreModule;
import org.visual.module.VisualModule;

@Getter
public enum DIContext {
  INSTANCE;

  private final Injector injector =
      Guice.createInjector(Stage.DEVELOPMENT, new LocalStoreModule(), new VisualModule());

  public <T> @NotNull T get(Class<T> clazz) {
    return injector.getInstance(clazz);
  }
}
