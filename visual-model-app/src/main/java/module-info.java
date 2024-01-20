module org.visual.model.app {
    requires javafx.graphics;
    requires static lombok;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires com.google.common;
    requires jakarta.inject;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.visual.model.ui;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.github.gestalt.core;
    requires org.github.gestalt.toml;
    requires java.logging;
    requires org.visual.model.shared;
    requires dev.dirs;
    requires org.visual.model.i18n;
    requires org.visual.model.database;
    requires io.avaje.inject;
    requires org.kordamp.ikonli.fluentui;
    requires org.visual.model.anntation;
    requires org.visual.model.debugger;
    requires info.picocli;
    requires org.visual.model.graph.editor;
    requires org.eclipse.emf.common;
    requires org.eclipse.emf.ecore.xmi;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.edit;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires io.avaje.validation.contraints;

    opens org.visual.model.app.controller to
            javafx.fxml;

    exports org.visual.model.app.core to
            javafx.graphics,
            javafx.fxml;
    exports org.visual.model.app.controller to
            javafx.fxml,
            javafx.graphics;
    exports org.visual.model.app.core to
            javafx.fxml,
            javafx.graphics;

    provides io.avaje.inject.spi.Module with
            org.visual.model.app.AppModule;
}
