module org.visual.config {
  requires static lombok;
  requires org.github.gestalt.core;
  requires io.avaje.inject;
  requires org.github.gestalt.toml;
  requires org.slf4j;
  requires dev.dirs;
  requires org.github.gestalt.yaml;
  requires org.github.gestalt.json;
  requires static org.jetbrains.annotations;
  requires jakarta.inject;

  exports org.visual.config;

  provides io.avaje.inject.spi.Module with
      org.visual.config.ConfigModule;
}
