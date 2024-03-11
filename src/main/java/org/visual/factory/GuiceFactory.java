package org.visual.factory;

import com.google.inject.ConfigurationException;
import lombok.extern.slf4j.Slf4j;
import org.visual.context.DIContext;
import picocli.CommandLine;

@Slf4j
public class GuiceFactory implements CommandLine.IFactory {

  @Override
  public <K> K create(Class<K> cls) throws Exception {
    try {
      return DIContext.INSTANCE.get(cls);
    } catch (ConfigurationException ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(cls); // fallback if missing
    }
  }
}
