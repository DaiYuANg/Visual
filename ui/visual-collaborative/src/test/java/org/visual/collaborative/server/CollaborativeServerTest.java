package org.visual.collaborative.server;

import org.junit.jupiter.api.Test;
import org.visual.collaborative.server.internal.CollaborativeServer;

class CollaborativeServerTest {

  private final CollaborativeServer server = new CollaborativeServer();

  @Test
  void start() {
    server.startServer();
  }
}
