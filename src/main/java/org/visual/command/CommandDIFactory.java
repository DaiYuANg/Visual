package org.visual.command;

import org.visual.context.ApplicationContext;
import picocli.CommandLine;

public class CommandDIFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    try {
      return ApplicationContext.INSTANCE.get(cls);
    } catch (Exception ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(cls); // fallback if missing
    }
  }
}
