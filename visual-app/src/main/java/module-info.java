module org.visual.app {
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires static io.avaje.spi;
  requires static io.soabase.recordbuilder.core;
  requires org.slf4j;
  requires com.google.common;
  requires jakarta.inject;
  requires javafx.controls;
  requires javafx.web;
  requires javafx.base;
  requires info.picocli;
  requires org.slf4j.jdk.platform.logging;
  requires io.smallrye.mutiny;
  requires it.unimi.dsi.fastutil;
  requires org.eclipse.collections.impl;
  requires org.eclipse.collections.api;
  requires org.jgrapht.core;
  requires atlantafx.base;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.fontawesome5;
  requires io.avaje.inject;
  requires io.vavr;
  requires jdk.jfr;
  requires org.kordamp.ikonli.fluentui;
  requires javafx.fxml;
  requires org.apache.commons.io;
  requires io.vertx.core;
  requires io.smallrye.mutiny.vertx.core;
  requires io.smallrye.mutiny.vertx.web.client;
  requires java.naming;
  requires java.compiler;
  requires org.apache.fury.core;
  requires io.smallrye.mutiny.vertx.web;
  requires org.controlsfx.controls;
  requires org.mapstruct;
  requires org.eclipse.jgit;
  requires org.apache.commons.lang3;
  requires org.apache.commons.pool2;
  requires io.avaje.inject.events;
  requires io.ebean.querybean;
  requires io.ebean.api;
  requires io.ebean.migration;
  requires io.avaje.validation.contraints;
  requires io.avaje.validation;

  requires com.jthemedetector;
  requires org.visual.core;
  requires org.visual.database;
  requires org.visual.config;

  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.databind;

  requires org.apache.commons.dbutils;
  requires org.apache.commons.text;

  requires java.desktop;

  requires dev.dirs;
  requires java.prefs;
  requires io.avaje.validation.plugin;
  requires io.avaje.applog;
  requires io.avaje.applog.slf4j;

  exports org.visual.app.view to
    javafx.graphics;

  requires org.visual.api;
  requires net.sourceforge.plantuml;
  exports org.visual.app.api;

  provides org.visual.api.Lifecycle with org.visual.app.lifecycle.BackgroundServiceLifecycle;
  provides io.avaje.inject.spi.InjectExtension with
    org.visual.app.AppModule;

}