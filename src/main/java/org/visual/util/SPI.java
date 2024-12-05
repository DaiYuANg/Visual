package org.visual.util;

import lombok.experimental.UtilityClass;

import java.util.ServiceLoader;

@UtilityClass
public class SPI {
  public <S> ServiceLoader<S> load(Class<S> service) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    return ServiceLoader.load(service, cl);
  }
}
