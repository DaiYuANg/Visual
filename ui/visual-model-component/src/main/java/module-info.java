module org.visual.model.ui {
    requires javafx.graphics;
    requires javafx.controls;
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

    exports org.visual.model.ui.bar;
    exports org.visual.model.ui.window;
    exports org.visual.model.ui.layout;
    exports org.visual.model.ui.widget;
    exports org.visual.model.ui.control;
    exports org.visual.model.ui.util;
    exports org.visual.model.ui.theme;

    opens org.visual.model.ui.theme to com.sun.jna;
    opens org.visual.model.ui.bar to javafx.graphics, javafx.fxml;
    opens org.visual.model.ui.window to javafx.graphics, javafx.fxml;
    opens org.visual.model.ui.layout to javafx.graphics, javafx.fxml;
    opens org.visual.model.ui.widget to javafx.graphics, javafx.fxml;
    opens org.visual.model.ui.control to javafx.graphics, javafx.fxml;
    exports org.visual.model.ui.base;
}
