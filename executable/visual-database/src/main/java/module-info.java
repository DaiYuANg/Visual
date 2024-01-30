module org.visual.database {
  requires org.jetbrains.annotations;
  requires org.slf4j;
  requires static lombok;
  requires org.visual.i18n;
  requires org.visual.shared;
  requires org.visual.component;
  requires io.avaje.inject;
  requires org.slf4j.jdk.platform.logging;
  requires io.avaje.lang;
  requires io.avaje.applog;
  requires javafx.fxml;
  requires javafx.web;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.graphics;
  requires javafx.swing;
  requires javafx.media;
  requires info.picocli;
  requires kotlin.stdlib;
  requires org.visual.collaborative.server;
  requires dev.dirs;
  requires java.prefs;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.databind;
  requires org.apache.maven.resolver;
  requires org.apache.maven.resolver.impl;
  requires org.apache.maven.resolver.named;
  requires org.apache.maven.resolver.spi;
  requires org.apache.maven.resolver.util;
  requires org.apache.maven.resolver.transport.jdk;
  requires org.apache.maven.resolver.supplier;
  requires org.apache.maven.resolver.connector.basic;
  requires org.visual.debugger;

  exports org.visual.database;
  exports org.visual.database.data;

  opens org.visual.database.controller to
      javafx.fxml;

  provides io.avaje.inject.spi.Module with
      org.visual.database.DatabaseModule;
}
