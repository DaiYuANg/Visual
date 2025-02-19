package org.visual.app.factory;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;

import java.util.Set;

@Factory
public class ServiceFactory {

  @Bean
  ServiceManager serviceManager(Set<Service> services) {
    return new ServiceManager(services);
  }
}
