module org.visual.model.text.editor {
    requires org.jetbrains.annotations;
    requires org.slf4j;
    requires static lombok;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.apache.commons.lang3;
    requires org.visual.model.shared;
    requires kotlin.stdlib;

    exports org.visual.model.text.editor;
}
