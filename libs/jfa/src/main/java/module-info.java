module org.visual.model.jfa {
  requires transitive com.sun.jna;
  requires org.jetbrains.annotations;
  requires static lombok;

  exports org.visual.model.jfa.core;
  exports org.visual.model.jfa.foundation;
  exports org.visual.model.jfa.util;
  exports org.visual.model.jfa.cleanup;
  exports org.visual.model.jfa.appkit;
  exports org.visual.model.jfa.annotation;

  opens org.visual.model.jfa.core to
      com.sun.jna,
      com.sun.jna.platform;
  opens org.visual.model.jfa.foundation to
      com.sun.jna,
      com.sun.jna.platform;
  opens org.visual.model.jfa.appkit to
      com.sun.jna,
      com.sun.jna.platform;
}
