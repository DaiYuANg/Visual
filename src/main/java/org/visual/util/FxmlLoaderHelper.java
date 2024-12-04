package org.visual.util;

import io.vavr.control.Try;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.ViewConstant;
import org.visual.context.DIContext;
import org.visual.view.VisualUI;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@Slf4j
public class FxmlLoaderHelper {

  public <T extends Parent> T load(@NotNull ViewConstant prefix) {
    return Try.of(() -> VisualUI.class.getResource(prefix.getViewName() + ".fxml"))
      .mapTry(FXMLLoader::new)
      .andThenTry(loader -> loader.setControllerFactory(DIContext.INSTANCE::get))
      .<T>mapTry(FXMLLoader::load)
      .onFailure(t -> log.atError().log(t.getMessage(), t))
      .get();
  }
}
