package org.visual.collaborative;

import org.junit.jupiter.api.Test;
import org.visual.collaborative.internal.CollaborativeServer;

class CollaborativeServerTest {

  private final CollaborativeServer server = new CollaborativeServer();

  @Test
  void start() {
    server.startServer();
  }
}
