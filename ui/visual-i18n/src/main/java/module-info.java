@SuppressWarnings({"requires-automatic"})
module org.visual.i18n {
  requires org.slf4j;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires javafx.base;
  requires com.google.guice;
  requires jakarta.inject;

  exports org.visual.i18n.api;
  exports org.visual.i18n.core;
}
