import io.avaje.inject.InjectModule;
import org.visual.core.CoreModule;

@InjectModule(requires = CoreModule.class)
module org.visual.collaboration.server {
  requires static org.jetbrains.annotations;
  requires static lombok;
  requires org.slf4j;
  requires io.avaje.inject;
  requires io.avaje.inject.events;
  requires io.vertx.core;
  requires io.smallrye.mutiny;
  requires io.smallrye.mutiny.vertx.core;
  requires io.smallrye.mutiny.vertx.web;
  requires org.github.gestalt.core;
  requires org.github.gestalt.yaml;
  requires java.compiler;
  requires io.soabase.recordbuilder.core;
  requires org.visual.core;
  requires org.agrona.core;
  requires it.unimi.dsi.fastutil;
  requires org.visual.data.structure;
  exports org.visual.collaboration.server;

  provides io.avaje.inject.spi.InjectExtension with
    org.visual.collaboration.server.ServerModule;
}