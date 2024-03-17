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
  requires org.hibernate.orm.hikaricp;
  requires java.compiler;
  requires io.github.oshai.kotlinlogging;
  requires kotlin.stdlib;
  requires jakarta.persistence;
  requires com.querydsl.core;
  requires com.querydsl.jpa;

  exports org.visual.local.store.api;
  exports org.visual.local.store.repository;
  exports org.visual.local.store.entity;
  exports org.visual.local.store;
  exports org.visual.local.store.lifecycle to
      com.google.guice;
//  exports org.visual.local.store.provider to
//      com.google.guice;
}
