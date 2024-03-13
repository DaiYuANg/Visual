module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires java.naming;
  requires com.google.common;
  requires java.compiler;
  requires static org.jetbrains.annotations;
  requires jakarta.inject;
  requires io.avaje.inject;
  requires org.hibernate.orm.core;
  requires org.hibernate.orm.graalvm;
  requires jakarta.persistence;

  exports org.visual.local.store.api;
  exports org.visual.local.store.entity;
  exports org.visual.local.store.repository;

  opens org.visual.local.store.entity to
      org.hibernate.orm.core;

  provides io.avaje.inject.spi.Module with
      org.visual.local.store.StoreModule;
}
