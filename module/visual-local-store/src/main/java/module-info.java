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
  requires jakarta.inject;
  requires io.avaje.inject;

  exports org.visual.local.store.entity;
  exports org.visual.local.store.api;

  opens org.visual.local.store.base to
      org.hibernate.orm.core;

  provides io.avaje.inject.spi.Module with
      org.visual.local.store.StoreModule;
}
