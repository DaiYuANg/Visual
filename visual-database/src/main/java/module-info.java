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
  requires schemacrawler;
  provides io.avaje.validation.spi.ValidationExtension with org.visual.database.valid.GeneratedValidatorComponent;

  provides io.avaje.inject.spi.InjectExtension with org.visual.database.DatabaseModule;
  exports org.visual.database;
}