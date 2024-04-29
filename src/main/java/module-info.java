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
  requires io.github.oshai.kotlinlogging;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.fontawesome5;

  exports org.visual.component to
      com.google.guice;
  exports org.visual.view to
      javafx.graphics;
  exports org.visual.provider to
      com.google.guice;
  exports org.visual.exception to
      com.google.guice;
}
