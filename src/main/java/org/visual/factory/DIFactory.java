package org.visual.factory;

import lombok.extern.slf4j.Slf4j;
import org.visual.context.DIContext;
import picocli.CommandLine;

@Slf4j
public class DIFactory implements CommandLine.IFactory {

  @Override
  public <K> K create(Class<K> cls) throws Exception {
    try {
      return DIContext.INSTANCE.get(cls);
    } catch (Exception ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(cls); // fallback if missing
    }
  }
}
