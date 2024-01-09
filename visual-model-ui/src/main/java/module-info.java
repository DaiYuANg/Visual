module org.visual.model.ui.component {
    requires javafx.graphics;
    requires javafx.controls;
    requires static lombok;
    requires com.github.oshi;
    requires org.apache.commons.lang3;
    requires org.jetbrains.annotations;

    exports org.visual.model.ui;
    exports org.visual.model.ui.window;
    exports org.visual.model.ui.bar;
}