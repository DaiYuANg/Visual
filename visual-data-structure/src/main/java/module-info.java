module org.visual.data.structure {
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires static org.immutables.value;
  requires org.eclipse.collections.api;

  requires org.jgrapht.core;
  requires org.slf4j;
  requires java.prefs;
  requires java.compiler;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.databind;
  exports org.visual.data.structure.collaboration;
  exports org.visual.data.structure.constant;
  exports org.visual.data.structure.graph;
  exports org.visual.data.structure.diagram;
  exports org.visual.data.structure.impl;
}