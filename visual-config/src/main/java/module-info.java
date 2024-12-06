module org.visual.config {
  requires static lombok;
  requires org.slf4j;
  requires org.github.gestalt.yaml;
  requires org.github.gestalt.core;
  requires org.github.gestalt.toml;
  requires io.avaje.inject;
  requires java.prefs;
  requires static io.soabase.recordbuilder.core;
  requires java.compiler;
  exports org.visual.config;
}