package org.visual.database;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.validation.Validator;

@Factory
public class ValidatorFactory {

  @Bean
  Validator validator(){
    return Validator.builder().build();
  }
}
