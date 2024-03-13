module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires java.naming;
  requires com.google.common;
  requires static org.jetbrains.annotations;
  requires jakarta.inject;
  requires com.google.guice;
  requires com.google.guice.extensions.persist;
  requires org.hibernate.orm.core;
  requires org.hibernate.orm.graalvm;
  requires jakarta.persistence;
  requires java.compiler;
  requires com.querydsl.core;

  exports org.visual.local.store.api;
  exports org.visual.local.store.repository;
  exports org.visual.local.store.entity;
  exports org.visual.local.store;

  opens org.visual.local.store.lifecycle to
      com.google.guice;
  opens org.visual.local.store.entity to
      org.hibernate.orm.core;
}
