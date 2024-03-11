package org.visual.context;

import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.constant.FXMLView;
import org.visual.view.VisualUI;

public enum UIContext {
  INSTANCE;

  @SneakyThrows
  public Parent load(@NotNull FXMLView fxmlKey) {
    val url = "%s.fxml".formatted(fxmlKey.getView());
    FXMLLoader loader = new FXMLLoader(VisualUI.class.getResource(url));
    loader.setControllerFactory(DIContext.INSTANCE::get);
    loader.setCharset(StandardCharsets.UTF_8);
    return loader.load();
  }
}
