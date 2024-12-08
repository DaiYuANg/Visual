import io.avaje.inject.InjectModule;
import org.visual.core.CoreModule;
@InjectModule(requires = CoreModule.class)
module org.visual.collaboration.server {
  requires static org.jetbrains.annotations;
  requires static lombok;
  requires org.slf4j;
  requires io.avaje.inject;
  requires io.avaje.inject.events;
  requires org.visual.core;
  requires io.vertx.core;
  requires io.smallrye.mutiny;
  requires io.smallrye.mutiny.vertx.core;
  requires io.smallrye.mutiny.vertx.tcp.eventbus.bridge;
  requires io.vertx.eventbusbridge.common;
  requires info.picocli;

  exports org.visual.collaboration.server;

  provides io.avaje.inject.spi.InjectExtension with
    org.visual.collaboration.server.ServerModule;
}