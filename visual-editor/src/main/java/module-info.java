module org.visual.editor {
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires javafx.controls;
  requires org.slf4j;
  requires java.compiler;
  requires org.visual.data.structure;
  requires static org.immutables.value;

  requires org.eclipse.collections.api;

  exports org.visual.editor.view.model;
  exports org.visual.editor.view;
  exports org.visual.editor.core;
}