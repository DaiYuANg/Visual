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

    exports org.visual.database;
    opens org.visual.database.controller to javafx.fxml;

    provides io.avaje.inject.spi.Module with org.visual.database.DatabaseModule;
}
