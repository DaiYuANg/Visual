module org.model.visual.model.debugger {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.instrument;
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

    opens org.visual.model.debugger.view.cssfx to javafx.fxml;
    opens org.visual.model.debugger.view.threedom to javafx.fxml;
    opens org.visual.model.debugger.remote to java.instrument, java.rmi;

    exports org.visual.model.debugger;
    exports org.visual.model.debugger.controller;
    exports org.visual.model.debugger.api;
    exports org.visual.model.debugger.event;
    exports org.visual.model.debugger.node;

    provides io.avaje.inject.spi.Module with org.visual.model.debugger.DebuggerModule;
}