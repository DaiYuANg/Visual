@SuppressWarnings({"requires-automatic"})
module org.visual.i18n {
  requires org.slf4j;
  requires static lombok;
  requires transitive org.jetbrains.annotations;
  requires javafx.base;

  exports org.visual.i18n;
}
