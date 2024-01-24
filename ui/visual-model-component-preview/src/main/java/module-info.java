module org.visual.model.component.preview {
    requires javafx.graphics;
    requires org.slf4j;
//    requires io.github.classgraph;
    requires org.visual.model.component.annotation;
    requires static lombok;
    requires org.visual.model.ui;
    requires org.slf4j.jdk.platform.logging;
    requires org.jetbrains.annotations;

    exports org.visual.model.component.preview;
    exports org.visual.model.component.preview.controller;
}