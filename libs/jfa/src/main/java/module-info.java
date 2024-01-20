module org.visual.model.jfa {
    requires transitive com.sun.jna;
    requires org.jetbrains.annotations;
    requires static lombok;

    exports org.visual.model.jfa.core;
    exports org.visual.model.jfa.foundation;
    exports org.visual.model.jfa.util;
    exports org.visual.model.jfa.cleanup;
    exports org.visual.model.jfa.appkit;

    opens org.visual.model.jfa.core to
            com.sun.jna;
}
