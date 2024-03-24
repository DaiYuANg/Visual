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
  requires java.logging;
  requires info.picocli;
  requires org.slf4j.jdk.platform.logging;
  requires org.immutables.value;
  requires org.immutables.builder;
  requires org.immutables.serial;
  requires org.immutables.annotate;
  requires java.compiler;
  requires it.unimi.dsi.fastutil;
  requires org.eclipse.collections.impl;
  requires org.eclipse.collections.api;
  requires org.jgrapht.core;
  requires atlantafx.base;
  requires com.google.guice;
  requires kotlin.stdlib;

  exports org.visual.view to
      javafx.fxml,
      javafx.graphics;
  exports org.visual.controller to
      javafx.fxml,
      com.google.guice;

  opens org.visual.controller to
      javafx.fxml;

  exports org.visual.component to
      javafx.fxml;

  opens org.visual.component to
      javafx.fxml;

  exports org.visual.provider to
      com.google.guice;
  exports org.visual.controller.layout to
      com.google.guice;
}
