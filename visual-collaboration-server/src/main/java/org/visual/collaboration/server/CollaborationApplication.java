package org.visual.collaboration.server;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaboration.server.command.PicoFactory;
import org.visual.collaboration.server.command.ServerCommand;
import org.visual.collaboration.server.context.DI;
import picocli.CommandLine;

@Slf4j
public class CollaborationApplication {

  public static void main(String[] args) {
    val commandLine = DI.INSTANCE.get(ServerCommand.class);
    new CommandLine(commandLine, new PicoFactory()).execute(args);
  }
}
