module org.visual.model.graph.editor {
    requires org.slf4j;
    requires static lombok;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires org.eclipse.emf.ecore;
    requires org.eclipse.emf.common;
    requires org.eclipse.emf.edit;
    requires org.jetbrains.annotations;
    requires org.eclipse.emf.ecore.xmi;

    exports org.visual.model.graph.editor.model;
    exports org.visual.model.graph.editor.core;
    exports org.visual.model.graph.editor.api;
    exports org.visual.model.graph.editor.core.view;
    exports org.visual.model.graph.editor.core.skins.defaults.connection;
    exports org.visual.model.graph.editor.core.connections;
    exports org.visual.model.graph.editor.core.connectors;
    exports org.visual.model.graph.editor.core.skins.defaults;
    exports org.visual.model.graph.editor.api.utils;
    exports org.visual.model.graph.editor.api.window;
}
