module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires java.naming;
  requires com.google.common;
  requires static org.jetbrains.annotations;
  requires jakarta.inject;
  requires io.ebean.annotation;
  requires io.ebean.api;
  requires io.ebean.platform.h2;
  requires io.ebean.core;
  requires io.ebean.core.type;
  requires com.google.guice;
  requires io.ebean.querybean;
  requires io.ebean.ddl.generator;
  requires java.compiler;
  requires io.github.oshai.kotlinlogging;
  requires kotlin.stdlib;

  exports org.visual.local.store.api;
  exports org.visual.local.store.repository;
  exports org.visual.local.store.entity;
  exports org.visual.local.store;
  exports org.visual.local.store.provider to
      com.google.guice;
}
