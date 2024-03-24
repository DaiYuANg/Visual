package org.visual.api;

import org.jetbrains.annotations.NotNull;

public interface ObjectDI {
  <T> @NotNull T get(Class<T> clazz);
}
