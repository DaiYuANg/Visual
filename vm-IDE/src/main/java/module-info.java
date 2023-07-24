module org.visual.model.ide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires static lombok;
    requires com.google.common;
    requires io.vertx.core;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires org.reflections;
    requires java.prefs;
    requires atlantafx.base;
    requires MaterialFX;

    opens org.visual.model to
            javafx.fxml;

    exports org.visual.model;
    exports org.visual.model.controllers;

    opens org.visual.model.controllers to
            javafx.fxml;

    exports org.visual.model.views;

    opens org.visual.model.views to
            javafx.fxml;

    exports org.visual.model.i18n;

    opens org.visual.model.i18n to
            javafx.fxml;

    exports org.visual.model.initializing;

    opens org.visual.model.initializing to
            javafx.fxml;

    exports org.visual.model.contexts;

    opens org.visual.model.contexts to
            javafx.fxml;
}
