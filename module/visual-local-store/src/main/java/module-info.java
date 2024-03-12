module org.visual.local.store {
  requires static lombok;
  requires dev.dirs;
  requires org.slf4j;
  requires java.naming;
  requires com.google.common;
  requires java.compiler;
  requires org.jetbrains.annotations;
  requires jakarta.inject;
  requires io.avaje.inject;

  exports org.visual.local.store.api;
  exports org.visual.local.store.entity;
  exports org.visual.local.store.repository;
  exports org.visual.local.store;

  provides io.avaje.inject.spi.Module with
      org.visual.local.store.StoreModule;
}
