module org.visual.script {
  requires static lombok;
  requires org.slf4j;
  requires org.apache.groovy;
  requires jakarta.inject;
  requires org.apache.groovy.jsr223;
  requires org.apache.groovy.jmx;
  requires io.avaje.inject;

  provides io.avaje.inject.spi.Module with
      org.visual.script.provider.ProviderModule;
}
