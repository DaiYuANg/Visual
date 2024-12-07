package org.visual.app.ui;

import com.google.common.base.Preconditions;
import io.vavr.control.Try;
import jakarta.inject.Singleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.jetbrains.annotations.NotNull;
import org.visual.app.constant.ViewConstant;
import org.visual.app.context.DIContext;
import org.visual.app.view.VisualUI;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class FXMLHelper {

  private final FXMLLoadListener loadListener;

  private final MutableMap<ViewConstant, Parent> fxmlCache = Maps.mutable.empty();

  public <T extends Parent> T load(@NotNull ViewConstant prefix, @NotNull Class<T> type) {
    return Try.of(() -> fxmlCache.getIfAbsent(prefix, () -> loadFromDisk(prefix)))
      .andThenTry(view -> Preconditions.checkArgument(type.isInstance(view), "FXML cache contains a mismatched type."))
      .map(type::cast)
      .get();
  }

  private <T extends Parent> T loadFromDisk(@NotNull ViewConstant prefix) {
    return Try.of(() -> VisualUI.class.getResource(prefix.getViewName() + ".fxml"))
      .mapTry(FXMLLoader::new)
      .andThenTry(this::loaderSetup)
      .<T>mapTry(FXMLLoader::load)
      .onFailure(t -> log.atError().log(t.getMessage(), t))
      .get();
  }

  private void loaderSetup(@NotNull FXMLLoader loader) {
    loader.setControllerFactory(DIContext.INSTANCE::get);
    loader.setLoadListener(loadListener);
  }
}
