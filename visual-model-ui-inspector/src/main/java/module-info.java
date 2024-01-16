module org.model.visual.model.ui.inspector {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.instrument;
    requires java.rmi;
    requires java.logging;
    requires org.slf4j;
    requires jdk.attach;
    requires java.desktop;
    requires static lombok;
    requires org.jetbrains.annotations;
    requires javafx.web;
    requires javafx.swing;
    requires org.visual.model.shared;
    requires org.visual.model.ui;

    opens org.visual.model.ui.inspector.view.cssfx to javafx.fxml;
    opens org.visual.model.ui.inspector.view.threedom to javafx.fxml;
    opens org.visual.model.ui.inspector.remote to java.instrument, java.rmi;


    exports org.visual.model.ui.inspector;
}