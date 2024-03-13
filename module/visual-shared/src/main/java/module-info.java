@SuppressWarnings({"requires-automatic"})
module org.visual.shared {
  requires transitive org.apache.commons.lang3;
  requires transitive org.slf4j;
  requires com.google.common;
  requires transitive com.github.oshi;
  requires com.fasterxml.jackson.core;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.mapstruct;
  requires java.prefs;

  exports org.visual.shared.pojo;
  exports org.visual.shared.util;
  exports org.visual.shared.funcational;
  exports org.visual.shared.singleton;
}
