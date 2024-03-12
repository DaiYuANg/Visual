package org.visual.component.util;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

@UtilityClass
public class ObserveUtil {
  @Contract("_, _ -> new")
  public @NotNull @Unmodifiable List<ChangeListener<? super Number>> observeWidthHeight(
      Region observed, Region modified) {
    return observeWidthHeight(observed, modified, 0, 0);
  }

  @Contract("_, _, _, _ -> new")
  @SuppressWarnings("DuplicatedCode")
  public @NotNull @Unmodifiable List<ChangeListener<? super Number>> observeWidthHeight(
      Region observed, Region modified, double wDelta, double hDelta) {
    return List.of(
        observeWidth(observed, modified, wDelta), observeHeight(observed, modified, hDelta));
  }

  public @NotNull ChangeListener<? super Number> observeWidth(Region observed, Region modified) {
    return observeWidth(observed, modified, 0);
  }

  @SuppressWarnings("DuplicatedCode")
  public @NotNull ChangeListener<? super Number> observeWidth(
      @NotNull Region observed, Region modified, double wDelta) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> {
          if (now == null) return;
          var w = now.doubleValue();
          modified.setPrefWidth(w + wDelta);
        };
    observed.widthProperty().addListener(lsn);
    val current = observed.getWidth();
    if (current > 0) {
      lsn.changed(null, null, current);
    }
    return lsn;
  }

  public @NotNull ChangeListener<? super Number> observeHeight(Region observed, Region modified) {
    return observeHeight(observed, modified, 0);
  }

  @SuppressWarnings("DuplicatedCode")
  public static @NotNull ChangeListener<? super Number> observeHeight(
      @NotNull Region observed, Region modified, double hDelta) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> {
          if (now == null) return;
          var h = now.doubleValue();
          modified.setPrefHeight(h + hDelta);
        };
    observed.heightProperty().addListener(lsn);
    var current = observed.getHeight();
    if (current > 0) {
      lsn.changed(null, null, current);
    }
    return lsn;
  }

  @Contract("_, _ -> new")
  public @NotNull @Unmodifiable List<ChangeListener<? super Number>>
      observeWidthHeightWithPreferred(Region observed, Region modified) {
    return observeWidthHeightWithPreferred(observed, modified, 0, 0);
  }

  @Contract("_, _, _, _ -> new")
  @SuppressWarnings("DuplicatedCode")
  public @NotNull @Unmodifiable List<ChangeListener<? super Number>>
      observeWidthHeightWithPreferred(
          Region observed, Region modified, double wDelta, double hDelta) {
    return List.of(
        observeWidthWithPreferred(observed, modified, wDelta),
        observeHeightWithPreferred(observed, modified, hDelta));
  }

  public @NotNull ChangeListener<? super Number> observeWidthWithPreferred(
      Region observed, Region modified) {
    return observeWidthWithPreferred(observed, modified, 0);
  }

  @SuppressWarnings("DuplicatedCode")
  public @NotNull ChangeListener<? super Number> observeWidthWithPreferred(
      @NotNull Region observed, Region modified, double wDelta) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> {
          if (now == null) return;
          var w = now.doubleValue();
          modified.setPrefWidth(w + wDelta);
        };
    observed.widthProperty().addListener(lsn);
    observed.prefWidthProperty().addListener(lsn);
    var current = observed.getWidth();
    if (current <= 0) {
      current = observed.getPrefWidth();
    }
    if (current > 0) {
      lsn.changed(null, null, current);
    }
    return lsn;
  }

  public @NotNull ChangeListener<? super Number> observeHeightWithPreferred(
      Region observed, Region modified) {
    return observeHeightWithPreferred(observed, modified, 0);
  }

  @SuppressWarnings("DuplicatedCode")
  public @NotNull ChangeListener<? super Number> observeHeightWithPreferred(
      Region observed, Region modified, double hDelta) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> {
          if (now == null) return;
          var h = now.doubleValue();
          modified.setPrefHeight(h + hDelta);
        };
    observed.heightProperty().addListener(lsn);
    observed.prefHeightProperty().addListener(lsn);
    var current = observed.getHeight();
    if (current <= 0) {
      current = observed.getWidth();
    }
    if (current > 0) {
      lsn.changed(null, null, current);
    }
    return lsn;
  }

  public List<ChangeListener<? super Number>> observeWidthHeightCenter(
      Region observed, Region modified) {
    return List.of(observeWidthCenter(observed, modified), observeHeightCenter(observed, modified));
  }

  public ChangeListener<? super Number> observeWidthCenter(Region observed, Region modified) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> modified.setLayoutX((observed.getWidth() - modified.getWidth()) / 2);
    observed.widthProperty().addListener(lsn);
    modified.widthProperty().addListener(lsn);
    lsn.changed(null, null, null);
    return lsn;
  }

  public ChangeListener<? super Number> observeHeightCenter(Region observed, Region modified) {
    ChangeListener<? super Number> lsn =
        (ob, old, now) -> modified.setLayoutY((observed.getHeight() - modified.getHeight()) / 2);
    observed.heightProperty().addListener(lsn);
    modified.heightProperty().addListener(lsn);
    lsn.changed(null, null, null);
    return lsn;
  }
}
