module org.visual.database {
  requires io.avaje.validation;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.slf4j;
  requires io.avaje.validation.contraints;
  requires java.sql;
  requires org.apache.commons.text;
  requires io.vavr;
  requires io.avaje.inject;
  provides io.avaje.validation.spi.ValidationExtension with org.visual.database.valid.GeneratedValidatorComponent;
  exports org.visual.database;
}