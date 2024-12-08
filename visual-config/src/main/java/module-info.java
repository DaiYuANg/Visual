module org.visual.config {
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.slf4j;
  requires org.github.gestalt.yaml;
  requires org.github.gestalt.core;
  requires org.github.gestalt.toml;
  requires com.fasterxml.jackson.dataformat.yaml;
  requires io.avaje.inject;
  requires java.prefs;
  requires static io.soabase.recordbuilder.core;
  requires java.compiler;
  exports org.visual.config;
}