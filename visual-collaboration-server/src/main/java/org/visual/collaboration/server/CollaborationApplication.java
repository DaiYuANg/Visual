package org.visual.collaboration.server;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaboration.server.command.ServerCommand;
import org.visual.collaboration.server.context.DI;

@Slf4j
public class CollaborationApplication {

  public static void main(String[] args) {
    val command = DI.INSTANCE.get(ServerCommand.class);
    command.run();
  }
}
