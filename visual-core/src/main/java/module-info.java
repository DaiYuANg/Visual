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
  exports org.visual.core;
}