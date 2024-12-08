package org.visual.collaboration.server.command;

import org.visual.collaboration.server.context.DI;
import picocli.CommandLine;

public class PicoFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DI.INSTANCE.get(aClass);
    } catch (Exception ex) {
      return CommandLine.defaultFactory().create(aClass);
    }
  }
}
