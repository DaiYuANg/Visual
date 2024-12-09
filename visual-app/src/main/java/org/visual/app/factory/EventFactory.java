package org.visual.app.factory;

import io.avaje.inject.BeanScope;
import io.avaje.inject.Factory;
import io.github.joeljeremy.deezpatch.core.Deezpatch;
import io.github.joeljeremy.deezpatch.core.Dispatcher;

@Factory
public class EventFactory {
  Dispatcher dispatcher(BeanScope beanScope){
    return Deezpatch.builder()
      .instanceProvider(beanScope::get)
      .build();
  }
}
