module org.visual {
  requires javafx.graphics;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.slf4j;
  requires com.google.common;
  requires jakarta.inject;
  requires javafx.controls;
  requires javafx.web;
  requires java.logging;
  requires info.picocli;
  requires org.slf4j.jdk.platform.logging;
  requires java.compiler;
  requires io.smallrye.mutiny;
  requires it.unimi.dsi.fastutil;
  requires org.eclipse.collections.impl;
  requires org.eclipse.collections.api;
  requires org.jgrapht.core;
  requires atlantafx.base;
  requires kotlin.stdlib;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.fontawesome5;
  requires io.avaje.inject;
  requires io.soabase.recordbuilder.core;
  requires org.visual.store;

  exports org.visual.view to
      javafx.graphics;

  provides io.avaje.inject.spi.InjectExtension with
      org.visual.VisualModule;
}
