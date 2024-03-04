@SuppressWarnings({"requires-automatic"})
module org.visual.debugger {
  requires transitive javafx.fxml;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive java.instrument;
  requires java.rmi;
  requires java.logging;
  requires org.slf4j;
  requires jdk.attach;
  requires java.desktop;
  requires static lombok;
  requires org.jetbrains.annotations;
  requires javafx.web;
  requires javafx.swing;
  requires org.visual.shared;
  requires org.visual.component;
  requires com.google.guice;
  requires org.github.gestalt.core;
  requires java.management;
  requires java.management.rmi;
  requires org.apache.commons.lang3;
  requires org.visual.i18n;
  requires java.prefs;
  requires it.unimi.dsi.fastutil;
  requires kotlin.stdlib;
  requires org.slf4j.jdk.platform.logging;
  requires org.fxmisc.flowless;
  requires io.github.classgraph;
  requires net.bytebuddy;
  requires jakarta.inject;
  requires org.github.gestalt.guice;
  requires io.github.oshai.kotlinlogging;
  requires com.dlsc.preferencesfx;
  requires com.google.gson;

  opens org.visual.debugger.view.cssfx to
      javafx.fxml;
  opens org.visual.debugger.view.threedom to
      javafx.fxml;
  opens org.visual.debugger.view to
      com.google.guice;
  opens org.visual.debugger.remote to
      java.instrument,
      java.rmi;
  opens org.visual.debugger.component to
      javafx.fxml;
  opens org.visual.debugger.controller to
      javafx.fxml,
      com.google.guice;
  opens org.visual.debugger.listener to
      com.google.guice;

  exports org.visual.debugger.controller;
  exports org.visual.debugger.api;
  exports org.visual.debugger.event;
  exports org.visual.debugger.node;
  exports org.visual.debugger.configuration;
  exports org.visual.debugger.model.update;
  exports org.visual.debugger.inspector;
  exports org.visual.debugger.component;
  exports org.visual.debugger.module;
  exports org.visual.debugger.view;
}
