module org.visual.model.debugger {
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
    requires org.visual.model.shared;
    requires org.visual.model.ui;
    requires io.avaje.inject;
    requires org.github.gestalt.core;
    requires java.management;
    requires org.apache.commons.lang3;
    requires org.visual.model.i18n;
    requires java.prefs;
    requires it.unimi.dsi.fastutil;
    requires kotlin.stdlib;
    requires org.slf4j.jdk.platform.logging;
    requires org.fxmisc.flowless;
    requires io.github.classgraph;
    requires net.bytebuddy;

    opens org.visual.model.debugger.view.cssfx to javafx.fxml;
    opens org.visual.model.debugger.view.threedom to javafx.fxml;
    opens org.visual.model.debugger.remote to java.instrument, java.rmi;
    opens org.visual.model.debugger.component to javafx.fxml;
    opens org.visual.model.debugger.controller to javafx.fxml;

    exports org.visual.model.debugger.controller;
    exports org.visual.model.debugger.api;
    exports org.visual.model.debugger.event;
    exports org.visual.model.debugger.node;
    exports org.visual.model.debugger.configuration;
    exports org.visual.model.debugger.model.update;
    exports org.visual.model.debugger.inspector;
    exports org.visual.model.debugger.component;
    exports org.visual.model.debugger;

    provides io.avaje.inject.spi.Module with org.visual.model.debugger.DebuggerModule;
}
