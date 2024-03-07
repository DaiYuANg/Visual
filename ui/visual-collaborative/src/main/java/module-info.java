module org.visual.collaborative.server {
  requires io.vertx.core;
  requires static lombok;
  requires javafx.base;
  requires org.slf4j;
  requires io.vertx.clustermanager.hazelcast;
  requires java.transaction.xa;
  requires org.visual.shared;
  requires io.avaje.inject;
  requires static org.jetbrains.annotations;

  provides io.avaje.inject.spi.Module with
      org.visual.collaborative.server.factory.FactoryModule;

  exports org.visual.collaborative.server;
}
