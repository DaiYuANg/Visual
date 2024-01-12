module org.visual.model.ui {
    requires javafx.graphics;
    requires javafx.controls;
    requires static lombok;
    requires org.slf4j;
    requires com.google.common;
    requires com.github.oshi;
    requires org.jetbrains.annotations;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.fluentui;
    requires kotlin.stdlib;
    requires org.visual.model.shared;

    exports org.visual.model.ui to javafx.graphics;
    exports org.visual.model.ui.window;
    exports org.visual.model.ui.bar;
    exports org.visual.model.ui.layout;
    exports org.visual.model.ui.widget;
}