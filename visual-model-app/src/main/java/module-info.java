module orgvisual.model.app {
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
    requires org.visual.model.ui.component;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.github.gestalt.core;
    requires org.github.gestalt.toml;

    exports org.visual.model.app to javafx.graphics, javafx.fxml;
    exports org.visual.model.app.controller to
            javafx.fxml,
            javafx.graphics,
            com.google.guice;
    exports org.visual.model.app.provider to com.google.guice;
}