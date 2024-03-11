module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires org.hibernate.orm.core;
  requires org.hibernate.orm.graalvm;
  requires jakarta.persistence;
  requires java.naming;
  requires com.google.common;
  requires com.querydsl.core;
  requires java.compiler;
  requires com.querydsl.jpa;
  requires org.jetbrains.annotations;
  requires com.google.guice;
  requires com.google.guice.extensions.persist;
  requires jakarta.inject;

  exports org.visual.local.store.entity;
  exports org.visual.local.store.api;
  exports org.visual.local.store;

  opens org.visual.local.store.lifecycle to
      com.google.guice;
  opens org.visual.local.store.base to
      org.hibernate.orm.core;
  opens org.visual.local.store.repository to
      com.google.guice;
}
