package org.visual.app.context;

import com.google.common.base.Preconditions;
import io.avaje.inject.PostConstruct;
import io.avaje.inject.PreDestroy;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.fury.Fury;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.prefs.Preferences;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class ApplicationContext {
  private final Preferences preferences;
  private final Fury fury;
  private final MutableMap<String, Object> internal = Maps.mutable.<String, Object>empty().asSynchronized();

  @SneakyThrows
  @PostConstruct
  void postConstruct() {
    Arrays.stream(preferences.keys()).forEach(key -> {
      log.atInfo().log("PKey:{}", key);
    });
  }

  public void set(String key, Object value) {
    internal.put(key, value);
  }

  public <T> T get(String key, @NotNull Class<T> clazz) {
    val v = internal.get(key);
    Preconditions.checkArgument(clazz.isInstance(v));
    return clazz.cast(v);
  }

  @PreDestroy
  void preDestroy() {
    log.atInfo().log("Save Context");
    internal.forEach((key, value) -> {
      val objectBytes = fury.serializeJavaObjectAndClass(value);
      preferences.putByteArray(key, objectBytes);
    });
  }
}
