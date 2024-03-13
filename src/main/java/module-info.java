@SuppressWarnings({"requires-automatic"})
module org.visual {
  requires javafx.graphics;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.slf4j;
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
  requires org.slf4j.jdk.platform.logging;
  requires org.visual.local.store;
  requires com.google.guice;
  requires com.google.guice.extensions.jmx;
  requires com.google.guice.extensions.assistedinject;
  requires com.google.guice.extensions.throwingproviders;

  exports org.visual to
      com.google.guice;

  opens org.visual.controller to
      javafx.fxml;

  exports org.visual.core;
  exports org.visual.controller to
      javafx.fxml,
      javafx.graphics;
  exports org.visual.view to
      javafx.fxml,
      javafx.graphics;
  exports org.visual.component to
      javafx.fxml,
      javafx.graphics;
  exports org.visual.constant;

  opens org.visual.controller.layout to
      javafx.fxml;

  exports org.visual.controller.layout to
      javafx.fxml,
      javafx.graphics;

  opens org.visual.handle to
      com.google.guice;
  opens org.visual.provider to
      com.google.guice;
  opens org.visual.component to
      com.google.guice;
  opens org.visual.controller.creation to
      javafx.fxml;

  exports org.visual.controller.creation to
      javafx.fxml,
      javafx.graphics;
}
