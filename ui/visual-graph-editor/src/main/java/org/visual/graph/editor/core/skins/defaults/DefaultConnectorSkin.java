/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.core.skins.defaults;

import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import kotlin.NotImplementedError;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.GConnectorSkin;
import org.visual.graph.editor.api.GConnectorStyle;
import org.visual.graph.editor.core.connectors.DefaultConnectorTypes;
import org.visual.graph.editor.core.skins.defaults.utils.AnimatedColor;
import org.visual.graph.editor.core.skins.defaults.utils.ColorAnimationUtils;
import org.visual.graph.editor.model.GConnector;

/**
 * The default connector skin.
 *
 * <p>A connector that uses this skin must have one of the 8 types defined in {@link
 * DefaultConnectorTypes}. If the connector does not have one of these types, it will be set to
 * <b>left-input</b>.
 */
@Slf4j
public class DefaultConnectorSkin extends GConnectorSkin {

  private static final String STYLE_CLASS_BASE = "default-connector";

  private static final PseudoClass PSEUDO_CLASS_ALLOWED = PseudoClass.getPseudoClass("allowed");
  private static final PseudoClass PSEUDO_CLASS_FORBIDDEN = PseudoClass.getPseudoClass("forbidden");

  private static final String ALLOWED = "-animated-color-allowed";
  private static final String FORBIDDEN = "-animated-color-forbidden";

  private static final double SIZE = 25;

  private final Pane root = new Pane();
  private final Polygon polygon = new Polygon();

  private final AnimatedColor animatedColorAllowed;
  private final AnimatedColor animatedColorForbidden;

  /**
   * Creates a new default connector skin instance.
   *
   * @param connector the {@link GConnector} the skin is being created for
   */
  public DefaultConnectorSkin(final GConnector connector) {

    super(connector);

    performChecks();

    root.setManaged(false);
    root.resize(SIZE, SIZE);
    root.setPickOnBounds(false);

    polygon.setManaged(false);
    polygon.getStyleClass().addAll(STYLE_CLASS_BASE, connector.getType());

    drawTriangleConnector(connector.getType(), polygon);

    root.getChildren().add(polygon);

    animatedColorAllowed =
        new AnimatedColor(ALLOWED, Color.WHITE, Color.MEDIUMSEAGREEN, Duration.millis(500));
    animatedColorForbidden =
        new AnimatedColor(FORBIDDEN, Color.WHITE, Color.TOMATO, Duration.millis(500));
  }

  @Override
  public Node getRoot() {
    return root;
  }

  @Override
  public double getWidth() {
    return SIZE;
  }

  @Override
  public double getHeight() {
    return SIZE;
  }

  @Override
  public void applyStyle(final @NotNull GConnectorStyle style) {

    switch (style) {
      case DEFAULT -> {
        ColorAnimationUtils.removeAnimation(polygon);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, false);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, false);
      }
      case DRAG_OVER_ALLOWED -> {
        ColorAnimationUtils.animateColor(polygon, animatedColorAllowed);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, false);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, true);
      }
      case DRAG_OVER_FORBIDDEN -> {
        ColorAnimationUtils.animateColor(polygon, animatedColorForbidden);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_FORBIDDEN, true);
        polygon.pseudoClassStateChanged(PSEUDO_CLASS_ALLOWED, false);
      }
    }
  }

  /**
   * Draws the given polygon to have a triangular shape.
   *
   * @param type the connector type
   * @param polygon the polygon to be drawn
   */
  public static void drawTriangleConnector(final String type, final Polygon polygon) {

    switch (type) {
      case DefaultConnectorTypes.TOP_INPUT, DefaultConnectorTypes.BOTTOM_OUTPUT ->
          drawVertical(false, polygon);
      case DefaultConnectorTypes.TOP_OUTPUT, DefaultConnectorTypes.BOTTOM_INPUT ->
          drawVertical(true, polygon);
      case DefaultConnectorTypes.RIGHT_INPUT, DefaultConnectorTypes.LEFT_OUTPUT ->
          drawHorizontal(false, polygon);
      case DefaultConnectorTypes.RIGHT_OUTPUT, DefaultConnectorTypes.LEFT_INPUT ->
          drawHorizontal(true, polygon);
      default -> throw new IllegalStateException("Unexpected value: " + type);
    }
  }

  /**
   * Draws the polygon for a horizontal orientation, pointing right or left.
   *
   * @param pointingRight {@code true} to point right, {@code false} to point left
   * @param polygon the polygon to be drawn
   */
  private static void drawHorizontal(final boolean pointingRight, final Polygon polygon) {

    if (pointingRight) {
      polygon.getPoints().addAll(0D, 0D, SIZE, SIZE / 2, 0D, SIZE);
    } else {
      polygon.getPoints().addAll(SIZE, 0D, SIZE, SIZE, 0D, SIZE / 2);
    }
  }

  /**
   * Draws the polygon for a vertical orientation, pointing up or down.
   *
   * @param pointingUp {@code true} to point up, {@code false} to point down
   * @param polygon the polygon to be drawn
   */
  private static void drawVertical(final boolean pointingUp, final Polygon polygon) {

    if (pointingUp) {
      polygon.getPoints().addAll(SIZE / 2, 0D, SIZE, SIZE, 0D, SIZE);
    } else {
      polygon.getPoints().addAll(0D, 0D, SIZE, 0D, SIZE / 2, SIZE);
    }
  }

  /** Checks that the connector has the correct values to be displayed using this skin. */
  private void performChecks() {
    if (!DefaultConnectorTypes.isValid(getItem().getType())) {
      log.error(
          "Connector type '{}' not recognized, setting to 'left-input'.", getItem().getType());
      getItem().setType(DefaultConnectorTypes.LEFT_INPUT);
    }
  }

  @Override
  protected void selectionChanged(boolean isSelected) {
    // Not implemented
    throw new NotImplementedError();
  }
}
