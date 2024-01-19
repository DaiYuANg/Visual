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

    exports org.visual.model.app to javafx.graphics, javafx.fxml;
    exports org.visual.model.app.controller to
            javafx.fxml,
            javafx.graphics;

    provides io.avaje.inject.spi.Module with org.visual.model.app.AppModule;
}