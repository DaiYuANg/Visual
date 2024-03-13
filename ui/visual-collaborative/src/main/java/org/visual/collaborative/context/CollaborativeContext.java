package org.visual.collaborative.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

@Getter
public enum CollaborativeContext {
  INSTANCE;

  private final Injector injector = Guice.createInjector();
}
