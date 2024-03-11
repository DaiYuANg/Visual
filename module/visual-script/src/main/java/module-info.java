module org.visual.script {
  requires static lombok;
  requires org.slf4j;
  requires com.google.guice;
  requires org.apache.groovy;
  requires jakarta.inject;
  requires org.apache.groovy.jsr223;
  requires org.apache.groovy.jmx;

  exports org.visual.script;
}
