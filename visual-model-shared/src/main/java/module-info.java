module org.visual.model.shared {
    requires transitive org.apache.commons.lang3;
    requires transitive org.slf4j;
    requires com.google.common;
    requires it.unimi.dsi.fastutil;
    requires org.jgrapht.core;
    requires static lombok;
    requires org.jetbrains.annotations;

    exports org.visual.model.shared;
    exports org.visual.model.shared.pojo;
}
