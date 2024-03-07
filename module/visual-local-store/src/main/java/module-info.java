module org.visual.local.store {
  requires io.ebean.annotation;
  requires io.ebean.api;
  requires io.ebean.platform.h2;
  requires io.ebean.core;
  requires io.ebean.core.type;
  requires static lombok;
  requires io.avaje.inject;
  requires dev.dirs;
  requires io.ebean.querybean;
  requires org.slf4j;
  requires io.ebean.migration;
  requires io.ebean.ddl.generator;

  provides io.avaje.inject.spi.Module with
      org.visual.local.store.factory.FactoryModule;
}
