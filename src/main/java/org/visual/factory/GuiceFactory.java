package org.visual.factory;

import com.google.inject.ConfigurationException;
import org.visual.context.DIContext;
import picocli.CommandLine;

public class GuiceFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DIContext.INSTANCE.get(aClass);
    } catch (ConfigurationException ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(aClass); // fallback if missing
    }
  }
}
