@SuppressWarnings({"requires-automatic"})
module org.visual.i18n {
  requires org.slf4j;
  requires static lombok;
  requires transitive org.jetbrains.annotations;
  requires javafx.base;
  requires com.google.guice;

  exports org.visual.i18n.api;

  opens org.visual.i18n.service to
      com.google.guice;

  exports org.visual.i18n.core;
}
