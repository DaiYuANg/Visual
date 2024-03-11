module org.visual.collaborative.server {
  requires io.vertx.core;
  requires static lombok;
  requires javafx.base;
  requires org.slf4j;
  requires io.vertx.clustermanager.hazelcast;
  requires java.transaction.xa;
  requires org.visual.shared;
  requires jakarta.inject;
  requires static org.jetbrains.annotations;
  requires com.google.guice;

  exports org.visual.collaborative.server;
  exports org.visual.collaborative.server.internal;
}
