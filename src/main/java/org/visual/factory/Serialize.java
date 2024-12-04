package org.visual.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.apache.fury.Fury;
import org.apache.fury.config.Language;

@Factory
public class Serialize {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  Fury fury() {
    return Fury.builder().withLanguage(Language.JAVA)
      .withAsyncCompilation(true)
      .withStringCompressed(true)
      .withMetaShare(true)
      .requireClassRegistration(true)
      .build();
  }
}
