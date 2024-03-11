module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires org.hibernate.orm.core;
  requires org.hibernate.orm.graalvm;
  requires jakarta.persistence;
  requires java.naming;
  requires com.querydsl.core;
  requires java.compiler;
  requires com.querydsl.jpa;
  requires org.jetbrains.annotations;
  requires com.google.guice;
  requires com.google.guice.extensions.persist;
  requires jakarta.inject;

  exports org.visual.local.store.entity;
  exports org.visual.local.store.repository;
  exports org.visual.local.store;
}
