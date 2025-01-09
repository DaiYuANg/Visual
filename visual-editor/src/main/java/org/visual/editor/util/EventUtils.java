package org.visual.editor.util;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.visual.data.structure.impl.VNode;

import java.util.Map;

import static java.util.Objects.nonNull;


/**
 * Helper methods for JavaFX event handling
 */
@UtilityClass
public class EventUtils {

  public <N extends Node, T extends Event> void removeEventHandlers(
    final @NotNull Map<N, EventHandler<T>> pEventHandlers,
    final EventType<T> pType
  ) {
    pEventHandlers.entrySet().stream()
      .filter(entry ->
        nonNull(entry.getKey()) && nonNull(entry.getValue())
      )
      .forEach(entry ->
        entry.getKey().removeEventHandler(pType, entry.getValue())
      );
    pEventHandlers.clear();
  }

}
