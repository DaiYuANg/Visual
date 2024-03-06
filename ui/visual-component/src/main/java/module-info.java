@SuppressWarnings({"requires-automatic"})
module org.visual.component {
  requires transitive javafx.graphics;
  requires transitive javafx.controls;
  requires transitive javafx.fxml;
  requires static lombok;
  requires org.slf4j;
  requires com.google.common;
  requires com.github.oshi;
  requires org.jetbrains.annotations;
  requires transitive org.kordamp.ikonli.core;
  requires transitive org.kordamp.ikonli.javafx;
  requires transitive org.kordamp.ikonli.fontawesome5;
  requires transitive org.kordamp.ikonli.fluentui;
  requires transitive org.kordamp.ikonli.simpleicons;
  requires transitive org.kordamp.ikonli.devicons;
  requires transitive org.kordamp.ikonli.materialdesign2;
  requires org.visual.shared;
  requires org.apache.commons.lang3;
  requires transitive atlantafx.base;
  requires static com.sun.jna;
  requires static com.sun.jna.platform;
  requires transitive org.controlsfx.controls;
  requires eu.iamgio.animated;
  requires org.apache.commons.pool2;
  requires it.unimi.dsi.fastutil;
  requires javafx.media;
  requires javafx.swing;
  requires org.apache.commons.io;
  requires java.logging;

  exports org.visual.component.window;
  exports org.visual.component.control;
  exports org.visual.component.control.click;
  exports org.visual.component.control.button;
  exports org.visual.component.control.dialog;
  exports org.visual.component.control.scroll;
  exports org.visual.component.util;
  exports org.visual.component.theme;
  exports org.visual.component.container;
  exports org.visual.component.animation;
  exports org.visual.component.api;
  exports org.visual.component.wrapper;

  opens org.visual.component.theme to
      com.sun.jna;
  opens org.visual.component.window to
      javafx.graphics,
      javafx.fxml;
  opens org.visual.component.control to
      javafx.graphics,
      javafx.fxml;
  opens org.visual.component.container to
      javafx.fxml,
      javafx.graphics;
  opens org.visual.component.api to
      javafx.fxml,
      javafx.graphics;
}
