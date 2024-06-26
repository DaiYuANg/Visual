module org.visual.config {
  exports org.visual.config;

  requires dev.dirs;
  requires io.vavr;
  requires org.slf4j;
  requires static org.jetbrains.annotations;
  requires static lombok;
  requires org.apache.commons.io;
  requires org.github.gestalt.core;
  requires org.github.gestalt.toml;

  exports org.visual.config.model;
}
