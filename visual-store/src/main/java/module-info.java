module org.visual.store {
  requires org.slf4j;
  requires jakarta.persistence;
  requires io.avaje.inject;
  requires org.hibernate.orm.core;
  requires static lombok;
  requires java.naming;
  requires jakarta.annotation;
  requires com.querydsl.core;
  requires java.compiler;
  requires org.hibernate.orm.hikaricp;
  requires jakarta.transaction;
  requires jakarta.inject;
  requires jakarta.xml.bind;
  requires org.jboss.logging;
  requires org.hibernate.validator;
  requires com.fasterxml.classmate;
  requires net.bytebuddy;
  requires com.querydsl.jpa;
  requires org.hibernate.orm.jfr;
  requires org.hibernate.orm.graalvm;
  requires static org.jetbrains.annotations;

  opens org.visual.store.entity;

  exports org.visual.store.api;
  exports org.visual.store.entity;

  provides io.avaje.inject.spi.InjectExtension with
      org.visual.store.StoreModule;
}
