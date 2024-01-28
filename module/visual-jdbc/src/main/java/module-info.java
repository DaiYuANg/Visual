module org.visual.jdbc {
    requires com.zaxxer.hikari;
    requires static lombok;
    requires org.slf4j;
    requires java.sql;
    requires org.apache.maven.resolver;
    requires org.apache.maven.resolver.impl;
    requires org.apache.maven.resolver.named;
    requires org.apache.maven.resolver.spi;
    requires org.apache.maven.resolver.util;
    requires org.apache.maven.resolver.transport.jdk;
    requires org.apache.maven.resolver.supplier;
    requires org.apache.maven.resolver.connector.basic;
    requires org.mapstruct;
    requires org.jetbrains.annotations;
    requires org.antlr.antlr4.runtime;

    exports org.visual.database.core;
}