package org.visual.module;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.visual.local.store.VisualLocalStoreModule;

@Slf4j
public class RootModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new VisualLocalStoreModule());
  }
}
