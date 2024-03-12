package org.visual.collaborative.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;

@Getter
public enum CollaborativeContext {
  INSTANCE;

  private final BeanScope injector = BeanScope.builder().build();
}
