module org.visual.core {
  requires static org.jetbrains.annotations;
  requires io.avaje.inject;
  requires static lombok;
  requires com.github.oshi;
  requires io.vertx.core;
  requires io.smallrye.mutiny;
  requires io.smallrye.mutiny.vertx.core;
  requires io.vertx.clustermanager.hazelcast;
  requires com.hazelcast.core;
  requires org.slf4j;
  requires io.vertx.metrics.micrometer;
  requires io.netty.resolver.dns.classes.macos;
  requires io.netty.resolver.dns.macos.osx.aarch_64;
  exports org.visual.core;
}