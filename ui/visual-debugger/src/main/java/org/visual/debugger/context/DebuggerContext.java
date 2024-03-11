package org.visual.debugger.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.Debugger;
import org.visual.debugger.constant.FXMLKey;

public enum DebuggerContext {
  INSTANCE;

  private static final Injector injector = Guice.createInjector();

  public static <T> T get(Class<T> clazz) {
    return injector.getInstance(clazz);
  }

  @SneakyThrows
  public static Parent load(@NotNull FXMLKey fxmlKey) {
    FXMLLoader loader = new FXMLLoader(Debugger.class.getResource(fxmlKey.getKey() + ".fxml"));
    loader.setControllerFactory((Callback<Class<?>, Object>) DebuggerContext::get);
    loader.setCharset(StandardCharsets.UTF_8);
    return loader.load();
  }
}
