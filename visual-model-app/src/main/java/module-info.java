module org.visual.model.app {
    requires javafx.graphics;
    requires static lombok;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires com.google.guice;
    requires com.google.common;
    requires com.google.guice.extensions.assistedinject;
    requires com.google.guice.extensions.throwingproviders;
    requires jakarta.inject;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.visual.model.ui;
    requires kotlin.stdlib;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.github.gestalt.core;
    requires org.github.gestalt.toml;
    requires org.github.gestalt.guice;
    requires org.github.gestalt.config.kotlin;
    requires java.logging;
    requires org.visual.model.shared;
    requires dev.dirs;
    requires org.visual.model.i18n;
    requires org.visual.model.sql;

    exports org.visual.model.app to javafx.graphics, javafx.fxml;
    exports org.visual.model.app.controller to
            javafx.fxml,
            javafx.graphics,
            com.google.guice;
    exports org.visual.model.app.provider to com.google.guice;
    exports org.visual.model.app.module to com.google.guice;
    exports org.visual.model.app.config to kotlin.reflect;

    opens org.visual.model.app.controller to javafx.fxml,com.google.guice;
    opens org.visual.model.app.module to com.google.guice;
}