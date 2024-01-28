/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.api.utils;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.EditorElement;
import org.visual.graph.editor.api.impl.GraphEventManagerImpl;

/**
 * General properties for the graph editor.
 *
 * <p>For example, should the editor have 'bounds', or should objects be draggable outside the
 * editor area?
 *
 * <p>If a bound is <b>active</b>, objects that are dragged or resized in the editor should stop
 * when they hit the edge, and the editor region will not try to grow in size. Otherwise it will
 * grow up to its max size.
 *
 * <p>Also stores properties for whether the grid is visible and/or snap-to-grid is on.
 */
public class GraphEditorProperties implements GraphEventManager {

  /** The default max width of the editor region, set on startup. */
  public static final double DEFAULT_MAX_WIDTH = Double.MAX_VALUE;

  /** The default max height of the editor region, set on startup. */
  public static final double DEFAULT_MAX_HEIGHT = Double.MAX_VALUE;

  public static final double DEFAULT_BOUND_VALUE = 15;
  public static final double DEFAULT_GRID_SPACING = 12;

  /**
   * -- SETTER -- Sets the value of the north bound.
   *
   * <p>
   *
   * <p>-- GETTER -- Gets the value of the north bound.
   *
   * @param pNorthBoundValue the value of the north bound
   * @return the value of the north bound
   */
  // The distance from the editor edge at which the objects should stop when dragged / resized.
  @Getter @Setter private double northBoundValue = DEFAULT_BOUND_VALUE;

  /**
   * -- SETTER -- Sets the value of the south bound.
   *
   * <p>
   *
   * <p>-- GETTER -- Gets the value of the south bound.
   *
   * @param pSouthBoundValue the value of the south bound
   * @return the value of the south bound
   */
  @Getter @Setter private double southBoundValue = DEFAULT_BOUND_VALUE;

  /**
   * -- SETTER -- Sets the value of the east bound.
   *
   * <p>
   *
   * <p>-- GETTER -- Gets the value of the east bound.
   *
   * @param pEastBoundValue the value of the east bound
   * @return the value of the east bound
   */
  @Getter @Setter private double eastBoundValue = DEFAULT_BOUND_VALUE;

  /**
   * -- GETTER -- Gets the value of the west bound.
   *
   * <p>
   *
   * <p>-- SETTER -- Sets the value of the west bound.
   *
   * @return the value of the west bound
   * @param pWestBoundValue the value of the west bound
   */
  @Setter @Getter private double westBoundValue = DEFAULT_BOUND_VALUE;

  // Off by default.
  private final BooleanProperty gridVisible = new SimpleBooleanProperty(this, "gridVisible");
  private final BooleanProperty snapToGrid = new SimpleBooleanProperty(this, "snapToGrid");
  private final DoubleProperty gridSpacing =
      new SimpleDoubleProperty(this, "gridSpacing", DEFAULT_GRID_SPACING);

  private final Map<EditorElement, BooleanProperty> readOnly = new EnumMap<>(EditorElement.class);

  /**
   * -- GETTER -- Additional properties that may be added and referred to in custom skin
   * implementations.
   *
   * @return a map of custom properties
   */
  @Getter
  private final ObservableMap<String, String> customProperties = FXCollections.observableHashMap();

  private final GraphEventManager eventManager = new GraphEventManagerImpl();

  /** Creates a new editor properties instance containing a set of default properties. */
  public GraphEditorProperties() {}

  /**
   * Copy constructor.
   *
   * <p>Creates a new editor properties instance with all values copied over from an existing
   * instance.
   *
   * @param editorProperties an existing {@link GraphEditorProperties} instance
   */
  public GraphEditorProperties(final @NotNull GraphEditorProperties editorProperties) {
    northBoundValue = editorProperties.getNorthBoundValue();
    southBoundValue = editorProperties.getSouthBoundValue();
    eastBoundValue = editorProperties.getEastBoundValue();
    westBoundValue = editorProperties.getWestBoundValue();

    gridVisible.set(editorProperties.isGridVisible());
    snapToGrid.set(editorProperties.isSnapToGridOn());
    gridSpacing.set(editorProperties.getGridSpacing());

    editorProperties.readOnly.forEach(
        (key, value) ->
            readOnly.computeIfAbsent(key, k -> new SimpleBooleanProperty()).set(value.get()));

    customProperties.putAll(editorProperties.getCustomProperties());
  }

  /**
   * Checks if the background grid is visible.
   *
   * @return {@code true} if the background grid is visible, {@code false} if not
   */
  public boolean isGridVisible() {
    return gridVisible.get();
  }

  /**
   * Sets whether the background grid should be visible or not.
   *
   * @param pGridVisible {@code true} if the background grid should be visible, {@code false} if not
   */
  public void setGridVisible(final boolean pGridVisible) {
    gridVisible.set(pGridVisible);
  }

  /**
   * Gets the grid-visible property.
   *
   * @return a {@link BooleanProperty} tracking whether the grid is visible or not
   */
  public BooleanProperty gridVisibleProperty() {
    return gridVisible;
  }

  /**
   * Checks if snap-to-grid is on.
   *
   * @return {@code true} if snap-to-grid is on, {@code false} if not
   */
  public boolean isSnapToGridOn() {
    return snapToGrid.get();
  }

  /**
   * Sets whether snap-to-grid should be on.
   *
   * @param pSnapToGrid {@code true} if snap-to-grid should be on, {@code false} if not
   */
  public void setSnapToGrid(final boolean pSnapToGrid) {
    snapToGrid.set(pSnapToGrid);
  }

  /**
   * Gets the snap-to-grid property.
   *
   * @return a {@link BooleanProperty} tracking whether snap-to-grid is on or off
   */
  public BooleanProperty snapToGridProperty() {
    return snapToGrid;
  }

  /**
   * Gets the current grid spacing in pixels.
   *
   * @return the current grid spacing
   */
  public double getGridSpacing() {
    return gridSpacing.get();
  }

  /**
   * Sets the grid spacing to be used if the grid is visible and/or snap-to-grid is enabled.
   *
   * <p>Integer values are recommended to avoid sub-pixel positioning effects.
   *
   * @param pGridSpacing the grid spacing to be used
   */
  public void setGridSpacing(final double pGridSpacing) {
    gridSpacing.set(pGridSpacing);
  }

  /**
   * Gets the grid spacing property.
   *
   * @return the grid spacing {@link DoubleProperty}.
   */
  public DoubleProperty gridSpacingProperty() {
    return gridSpacing;
  }

  /**
   * Gets the read only property
   *
   * @param pType {@link EditorElement}
   * @return read only {@link BooleanProperty}
   */
  public BooleanProperty readOnlyProperty(final EditorElement pType) {
    Objects.requireNonNull(pType, "ElementType may not be null!");
    return readOnly.computeIfAbsent(pType, k -> new SimpleBooleanProperty());
  }

  /**
   * Returns whether or not the graph is in read only state.
   *
   * @param pType {@link EditorElement}
   * @return whether or not the graph is in read only state.
   */
  public boolean isReadOnly(final EditorElement pType) {
    return pType != null && readOnly.computeIfAbsent(pType, k -> new SimpleBooleanProperty()).get();
  }

  /**
   * @param pType {@link EditorElement}
   * @param pReadOnly {@code true} to set the graph editor in read only state or {@code false}
   *     (default) for edit state.
   */
  public void setReadOnly(final EditorElement pType, final boolean pReadOnly) {
    if (pType != null) {
      readOnly.computeIfAbsent(pType, k -> new SimpleBooleanProperty()).set(pReadOnly);
    }
  }

  @Override
  public boolean activateGesture(GraphInputGesture pGesture, Event pEvent, Object pOwner) {
    return eventManager.activateGesture(pGesture, pEvent, pOwner);
  }

  @Override
  public boolean finishGesture(GraphInputGesture pExpected, Object pOwner) {
    return eventManager.finishGesture(pExpected, pOwner);
  }
}
