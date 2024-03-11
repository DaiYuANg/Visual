module org.visual.config {
  requires static lombok;
  requires org.github.gestalt.core;
  requires org.github.gestalt.guice;
  requires org.github.gestalt.toml;
  requires com.google.guice;
  requires org.slf4j;
  requires dev.dirs;
  requires org.github.gestalt.yaml;
  requires org.github.gestalt.json;
  requires org.jetbrains.annotations;
  requires jakarta.inject;

  exports org.visual.config;
}
