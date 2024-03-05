module org.visual.jdbc {
  requires com.zaxxer.hikari;
  requires static lombok;
  requires org.slf4j;
  requires java.sql;
  requires org.mapstruct;
  requires org.jetbrains.annotations;
  requires org.antlr.antlr4.runtime;

  exports org.visual.jdbc.core;
}
