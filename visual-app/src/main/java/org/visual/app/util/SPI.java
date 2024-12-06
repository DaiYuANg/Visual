package org.visual.app.util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.ServiceLoader;

@UtilityClass
public class SPI {
  public <S> ServiceLoader<S> load(Class<S> service) {
    val cl = Thread.currentThread().getContextClassLoader();
    return ServiceLoader.load(service, cl);
  }
}
