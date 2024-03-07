@SuppressWarnings({"requires-automatic"})
module org.visual {
  requires javafx.graphics;
  requires static lombok;
  requires org.slf4j;
  requires org.jetbrains.annotations;
  requires com.google.common;
  requires jakarta.inject;
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.web;
  requires org.visual.component;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.fontawesome5;
  requires org.github.gestalt.core;
  requires org.github.gestalt.toml;
  requires java.logging;
  requires org.visual.shared;
  requires dev.dirs;
  requires org.visual.i18n;
  requires org.kordamp.ikonli.fluentui;
  requires org.visual.debugger;
  requires info.picocli;
  requires org.visual.graph.editor;
  requires org.eclipse.emf.common;
  requires org.eclipse.emf.ecore.xmi;
  requires org.eclipse.emf.ecore;
  requires org.eclipse.emf.edit;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires it.unimi.dsi.fastutil;
  requires org.slf4j.jdk.platform.logging;
  requires org.apache.maven.resolver;
  requires io.avaje.inject;

  opens org.visual.controller to
      javafx.fxml;

  exports org.visual.core;
  exports org.visual.controller to
      javafx.fxml,
      javafx.graphics;
  exports org.visual.view to
      javafx.fxml,
      javafx.graphics;
  exports org.visual;
  exports org.visual.component to
      javafx.fxml,
      javafx.graphics;

  provides io.avaje.inject.spi.Module with
      org.visual.VisualModule;
}
