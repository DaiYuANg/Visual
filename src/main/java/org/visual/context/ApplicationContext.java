package org.visual.context;

import io.avaje.inject.BeanScope;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.visual.constant.FXMLView;
import org.visual.view.VisualUI;

public enum ApplicationContext {
  INSTANCE;

  private static final BeanScope injector = BeanScope.builder().build();

  public <T> @NotNull T get(Class<T> clazz) {
    return injector.get(clazz);
  }

  @SneakyThrows
  public Parent load(@NotNull FXMLView fxmlKey) {
    FXMLLoader loader = new FXMLLoader(VisualUI.class.getResource(STR."\{fxmlKey.getView()}.fxml"));
    loader.setControllerFactory(this::get);
    loader.setCharset(StandardCharsets.UTF_8);
    return loader.load();
  }
}
