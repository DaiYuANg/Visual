@SuppressWarnings({"requires-automatic"})
module org.visual.graph.editor {
  requires org.slf4j;
  requires static lombok;
  requires transitive javafx.graphics;
  requires transitive javafx.controls;
  requires transitive javafx.base;
  requires org.eclipse.emf.ecore;
  requires org.eclipse.emf.common;
  requires org.eclipse.emf.edit;
  requires static org.jetbrains.annotations;
  requires org.eclipse.emf.ecore.xmi;
  requires it.unimi.dsi.fastutil;
  requires org.jgrapht.core;
  requires org.visual.shared;
  requires org.visual.component;

  exports org.visual.graph.editor.model;
  exports org.visual.graph.editor.core;
  exports org.visual.graph.editor.api;
  exports org.visual.graph.editor.core.view;
  exports org.visual.graph.editor.core.skins.defaults.connection;
  exports org.visual.graph.editor.core.connections;
  exports org.visual.graph.editor.core.connectors;
  exports org.visual.graph.editor.core.skins.defaults;
  exports org.visual.graph.editor.api.utils;
  exports org.visual.graph.editor.api.window;
  exports org.visual.graph.editor.core.skins.defaults.connection.segment;
}
