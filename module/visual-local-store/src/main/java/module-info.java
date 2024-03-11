module org.visual.local.store {
  requires static lombok;
  requires io.avaje.inject;
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

  exports org.visual.local.store.entity;
  exports org.visual.local.store.repository;

  provides io.avaje.inject.spi.Module with
      org.visual.local.store.StoreModule;
}
