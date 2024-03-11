package org.visual.collaborative.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

public enum CollaborativeContext {
  INSTANCE;

  @Getter private final Injector injector = Guice.createInjector();
}
