package org.visual.local.store.lifecycle;

import com.google.inject.persist.PersistService;
import jakarta.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class Initializer {
  @Inject
  Initializer(@NotNull PersistService service) {
    service.start();
  }
}
