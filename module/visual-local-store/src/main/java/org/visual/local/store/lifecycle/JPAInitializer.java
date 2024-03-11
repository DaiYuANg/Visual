package org.visual.local.store.lifecycle;

import com.google.inject.persist.PersistService;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class JPAInitializer {

  @Inject
  JPAInitializer(@NotNull PersistService service) {
    service.start();
  }
}
