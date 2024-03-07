package org.visual.collaborative.server.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;

public enum CollaborativeContext {
  INSTANCE;

  @Getter private final BeanScope beanScope = BeanScope.builder().shutdownHook(true).build();
}
