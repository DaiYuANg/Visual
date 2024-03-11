package org.visual.script.provider;

import groovy.lang.GroovyShell;
import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroovyProvider implements Provider<GroovyShell> {
  @Override
  public GroovyShell get() {
    return new GroovyShell();
  }
}
