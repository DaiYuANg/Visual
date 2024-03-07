package org.visual.collaborative.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CollaborativeServerTest {

  private final CollaborativeServer server = new CollaborativeServer();

  @Test
  void start() {
    server.startServer();
  }
}
