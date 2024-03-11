package org.visual.script;

import com.google.inject.AbstractModule;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;
import org.visual.script.provider.GroovyProvider;

@Slf4j
public class ScriptModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(GroovyShell.class).toProvider(GroovyProvider.class);
  }
}
