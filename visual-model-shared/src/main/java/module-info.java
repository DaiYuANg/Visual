module org.visual.model.shared {
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires com.google.common;
    requires it.unimi.dsi.fastutil;
    requires org.jgrapht.core;
    requires static lombok;

    exports org.visual.model.shared;
}