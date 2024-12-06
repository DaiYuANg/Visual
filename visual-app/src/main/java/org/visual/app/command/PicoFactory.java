package org.visual.app.command;

import org.visual.app.context.DIContext;
import picocli.CommandLine;

public class PicoFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DIContext.INSTANCE.get(aClass);
    } catch (Exception ex) {
      return CommandLine.defaultFactory().create(aClass);
    }
  }
}
