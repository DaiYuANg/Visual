package org.visual.debugger.controller;

import io.avaje.inject.BeanScope;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum DIContainer {
  INSTANCE;
  private final BeanScope beanScope = BeanScope.builder().build();
}
