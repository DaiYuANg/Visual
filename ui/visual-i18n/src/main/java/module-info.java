@SuppressWarnings({"requires-automatic"})
module org.visual.i18n {
  requires org.slf4j;
  requires static lombok;
  requires transitive org.jetbrains.annotations;
  requires javafx.base;
  requires io.avaje.inject;

  exports org.visual.i18n.api;
  exports org.visual.i18n.core;

  provides io.avaje.inject.spi.Module with
      org.visual.i18n.service.ServiceModule;
}
