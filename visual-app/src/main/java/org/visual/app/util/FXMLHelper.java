package org.visual.app.util;

import com.google.common.base.Preconditions;
import io.vavr.control.Try;
import jakarta.inject.Singleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.jetbrains.annotations.NotNull;
import org.visual.app.constant.ViewConstant;
import org.visual.app.context.DIContext;
import org.visual.app.view.VisualUI;

import java.nio.charset.StandardCharsets;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class FXMLHelper {

  private final String fxmlSuffix = ".fxml";

  private final FXMLLoadListener loadListener;

  private final MutableMap<ViewConstant, Pair<Parent, Object>> fxmlCache = Maps.mutable.empty();

  public <T extends Parent> T loadView(@NotNull ViewConstant prefix, @NotNull Class<T> type) {
    return Try.of(() -> fxmlCache.getIfAbsent(prefix, () -> loadFromDisk(prefix)))
      .mapTry(Pair::getKey)
      .andThenTry(view -> Preconditions.checkArgument(type.isInstance(view), "FXML cache contains a mismatched type."))
      .map(type::cast)
      .onFailure(throwable -> log.atError().log(throwable.getMessage(), throwable))
      .get();
  }

  public <T extends Parent> T loadController(@NotNull ViewConstant prefix, @NotNull Class<T> type) {
    return Try.of(() -> fxmlCache.getIfAbsent(prefix, () -> loadFromDisk(prefix)))
      .mapTry(Pair::getValue)
      .andThenTry(view -> Preconditions.checkArgument(type.isInstance(view), "Controller cache contains a mismatched type."))
      .map(type::cast)
      .get();
  }

  public <T extends Parent, C> Pair<T, C> load(@NotNull ViewConstant prefix, @NotNull Class<T> type, Class<C> clazz) {
    return Try.of(() -> fxmlCache.getIfAbsent(prefix, () -> loadFromDisk(prefix)))
      .andThenTry(view -> {
        Preconditions.checkArgument(type.isInstance(view.getKey()), "FXML cache contains a mismatched type.");
        Preconditions.checkArgument(clazz.isInstance(view.getValue()), "Controller cache contains a mismatched type.");
      })
      .map(obj -> new Pair<>(type.cast(obj.getKey()), clazz.cast(obj.getValue())))
      .get();
  }

  private <T extends Parent, C> Pair<T, C> loadFromDisk(@NotNull ViewConstant prefix) {
    return Try.of(() -> VisualUI.class.getResource(prefix.getViewName() + fxmlSuffix))
      .mapTry(FXMLLoader::new)
      .andThenTry(this::loaderSetup)
      .<Pair<T, C>>mapTry(fxmlLoader -> new Pair<>(fxmlLoader.load(), fxmlLoader.getController()))
      .onFailure(t -> log.atError().log(t.getMessage(), t))
      .get();
  }

  private void loaderSetup(@NotNull FXMLLoader loader) {
    loader.setControllerFactory(DIContext.INSTANCE::get);
    loader.setLoadListener(loadListener);
    loader.setCharset(StandardCharsets.UTF_8);
  }
}
