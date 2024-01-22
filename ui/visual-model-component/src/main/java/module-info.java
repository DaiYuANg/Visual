module org.visual.model.ui {
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
    requires org.visual.model.shared;
    requires org.apache.commons.lang3;
    requires org.visual.model.anntation;
    requires transitive atlantafx.base;
    requires static com.sun.jna;
    requires org.visual.model.jfa;
    requires static com.sun.jna.platform;
    requires transitive org.controlsfx.controls;
    requires eu.iamgio.animated;
    requires kotlin.stdlib;

    exports org.visual.model.component.bar;
    exports org.visual.model.component.window;
    exports org.visual.model.component.layout;
    exports org.visual.model.component.widget;
    exports org.visual.model.component.control;
    exports org.visual.model.component.util;
    exports org.visual.model.component.theme;

    opens org.visual.model.component.theme to com.sun.jna;
    opens org.visual.model.component.bar to javafx.graphics, javafx.fxml;
    opens org.visual.model.component.window to javafx.graphics, javafx.fxml;
    opens org.visual.model.component.layout to javafx.graphics, javafx.fxml;
    opens org.visual.model.component.widget to javafx.graphics, javafx.fxml;
    opens org.visual.model.component.control to javafx.graphics, javafx.fxml;
    exports org.visual.model.component.base;
}
