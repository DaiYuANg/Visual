package org.visual.config;

import com.google.inject.AbstractModule;
import lombok.val;
import org.github.gestalt.config.guice.GestaltModule;
import org.visual.config.provider.ConfigProvider;

public class ConfigModule extends AbstractModule {
  @Override
  protected void configure() {
    val gestalt = new ConfigProvider().get();
    install(new GestaltModule(gestalt));
  }
}
