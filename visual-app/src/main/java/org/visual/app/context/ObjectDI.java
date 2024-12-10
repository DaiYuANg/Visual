package org.visual.app.context;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ObjectDI {
  <T> @NotNull T get(Class<T> clazz);

  <T> @NotNull List<T> getAll(Class<T> clazz);
}
