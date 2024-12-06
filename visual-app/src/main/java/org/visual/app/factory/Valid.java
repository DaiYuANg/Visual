package org.visual.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.validation.Validator;

@Factory
public class Valid {

  @Bean
  Validator validator() {
    return Validator.builder().failFast(true).build();
  }
}
