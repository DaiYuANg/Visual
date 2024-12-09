package org.visual.collaboration.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.agrona.concurrent.IdGenerator;
import org.agrona.concurrent.SnowflakeIdGenerator;

@Factory
public class IdFactory {

  @Bean
  IdGenerator idGenerator (){
    return new SnowflakeIdGenerator(1);
  }
}
