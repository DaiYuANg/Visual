@SuppressWarnings({"requires-automatic"})
module org.visual.model.shared {
    requires transitive org.apache.commons.lang3;
    requires transitive org.slf4j;
    requires com.google.common;
    requires it.unimi.dsi.fastutil;
    requires org.jgrapht.core;
    requires transitive com.github.oshi;
    requires com.fasterxml.jackson.core;
    requires static lombok;
    requires org.jetbrains.annotations;
    requires org.mapstruct;
    requires java.prefs;
    requires kotlin.stdlib;

    exports org.visual.model.shared;
    exports org.visual.model.shared.pojo;
    exports org.visual.model.shared.util;
}
