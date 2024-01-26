@SuppressWarnings({ "requires-automatic"})
module org.visual.model.i18n {
    requires org.slf4j;
    requires static lombok;
    requires transitive org.jetbrains.annotations;

    exports org.visual.model.i18n.core;
}
