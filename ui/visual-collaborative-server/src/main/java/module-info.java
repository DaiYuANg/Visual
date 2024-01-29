module org.visual.collaborative.server {
    requires io.vertx.core;
    requires static lombok;
    requires javafx.base;
    requires org.slf4j;
    requires io.vertx.clustermanager.hazelcast;
    requires kotlin.stdlib;
    requires java.transaction.xa;
    requires org.visual.shared;

    exports org.visual.collaborative.server;
}