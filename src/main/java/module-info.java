module org.visual.model.visualmodel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires static lombok;
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires org.reflections;
    //    requires io.vertx.core;
    requires java.prefs;
    //    requires com.jthemedetector;
    requires atlantafx.base;

    opens org.visual.model to
            javafx.fxml;

    exports org.visual.model;
    exports org.visual.model.controllers;

    opens org.visual.model.controllers to
            javafx.fxml;

    exports org.visual.model.components;

    opens org.visual.model.components to
            javafx.fxml;

    exports org.visual.model.i18n;

    opens org.visual.model.i18n to
            javafx.fxml;

    exports org.visual.model.initializing;

    opens org.visual.model.initializing to
            javafx.fxml;
    exports org.visual.model.contexts;
    opens org.visual.model.contexts to javafx.fxml;
}
