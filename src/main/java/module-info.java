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
  requires java.logging;
  requires org.visual.shared;
  requires dev.dirs;
  requires org.visual.i18n;
  requires org.kordamp.ikonli.fluentui;
  requires org.visual.debugger;
  requires org.visual.collaborative;
  requires org.visual.config;
  requires info.picocli;
  requires org.visual.graph.editor;
  requires org.eclipse.emf.common;
  requires org.eclipse.emf.ecore.xmi;
  requires org.eclipse.emf.ecore;
  requires org.eclipse.emf.edit;
  requires com.fasterxml.jackson.core;
  requires it.unimi.dsi.fastutil;
  requires org.slf4j.jdk.platform.logging;
  requires com.google.guice;
  requires com.google.guice.extensions.grapher;
  requires com.google.guice.extensions.jmx;
  requires org.visual.local.store;

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
  exports org.visual.constant;
}
