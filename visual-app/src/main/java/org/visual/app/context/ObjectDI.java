package org.visual.app.context;


import org.jetbrains.annotations.NotNull;

public interface ObjectDI {
  <T> @NotNull T get(Class<T> clazz);
}
