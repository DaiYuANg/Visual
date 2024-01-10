module org.visual.model.ui.component {
    requires javafx.graphics;
    requires javafx.controls;
    requires static lombok;
    requires com.github.oshi;
    requires org.apache.commons.lang3;
    requires org.jetbrains.annotations;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.fluentui;

    exports org.visual.model.ui;
    exports org.visual.model.ui.window;
    exports org.visual.model.ui.bar;
}