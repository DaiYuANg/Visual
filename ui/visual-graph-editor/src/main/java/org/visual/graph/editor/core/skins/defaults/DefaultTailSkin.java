/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.core.skins.defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import kotlin.NotImplementedError;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.GTailSkin;
import org.visual.graph.editor.api.utils.GeometryUtils;
import org.visual.graph.editor.core.connectors.DefaultConnectorTypes;
import org.visual.graph.editor.core.skins.defaults.tail.RectangularPathCreator;
import org.visual.graph.editor.model.GConnector;

/**
 * The default tail skin.
 *
 * <p>The styling is intended to match with the default connector and connection skins. See those
 * classes for more information.
 */
@Slf4j
public class DefaultTailSkin extends GTailSkin {
  private static final double ENDPOINT_SIZE = 25;

  private static final String STYLE_CLASS = "default-tail";
  private static final String STYLE_CLASS_ENDPOINT = "default-tail-endpoint";

  protected final Polyline line = new Polyline();
  protected final Polygon endpoint = new Polygon();
  protected final Group group = new Group(line, endpoint);

  /**
   * Creates a new default tail skin instance.
   *
   * @param connector the {@link GConnector} the skin is being created for
   */
  public DefaultTailSkin(final GConnector connector) {

    super(connector);

    performChecks();

    DefaultConnectorSkin.drawTriangleConnector(connector.getType(), endpoint);

    endpoint.getStyleClass().addAll(STYLE_CLASS_ENDPOINT, connector.getType());
    line.getStyleClass().setAll(STYLE_CLASS);
    group.setManaged(false);
  }

  @Override
  public Node getRoot() {
    return group;
  }

  @Override
  public void draw(final Point2D start, final Point2D end) {

    endpoint.setVisible(true);
    layoutEndpoint(end);
    drawStupid(start, end);
  }

  @Override
  public void draw(
      final Point2D start, final Point2D end, final GConnector target, final boolean valid) {

    endpoint.setVisible(false);
    if (valid) {
      drawSmart(start, end, target);
    } else {
      drawStupid(start, end);
    }
  }

  @Override
  public void draw(final Point2D start, final Point2D end, final List<Point2D> jointPositions) {
    draw(start, end);
  }

  @Override
  public void draw(
      final Point2D start,
      final Point2D end,
      final List<Point2D> jointPositions,
      final GConnector target,
      final boolean valid) {
    draw(start, end, target, valid);
  }

  @Override
  public List<Point2D> allocateJointPositions() {

    final List<Point2D> jointPositions = new ArrayList<>();

    IntStream.iterate(2, i -> i < line.getPoints().size() - 2, i -> i + 2)
        .forEach(
            i -> {
              final double x = GeometryUtils.moveOnPixel(line.getPoints().get(i));
              final double y = GeometryUtils.moveOnPixel(line.getPoints().get(i + 1));
              jointPositions.add(new Point2D(x, y));
            });

    return jointPositions;
  }

  /**
   * Sets layout values of the endpoint based on the new cursor position.
   *
   * @param position the new cursor position
   */
  protected void layoutEndpoint(final @NotNull Point2D position) {
    endpoint.setLayoutX(GeometryUtils.moveOnPixel(position.getX() - ENDPOINT_SIZE / 2));
    endpoint.setLayoutY(GeometryUtils.moveOnPixel(position.getY() - ENDPOINT_SIZE / 2));
  }

  /** Checks that the connector has the correct values to use this skin. */
  private void performChecks() {
    if (DefaultConnectorTypes.isValid(getItem().getType())) {
      return;
    }
    log.error("Connector type '{}' not recognized, setting to 'left-input'.", getItem().getType());
    getItem().setType(DefaultConnectorTypes.LEFT_INPUT);
  }

  /**
   * Draws the tail simply from the start position to the end.
   *
   * @param start the start position of the tail
   * @param end the end position of the tail
   */
  private void drawStupid(final Point2D start, final Point2D end) {

    clearPoints();
    addPoint(start);

    if (Objects.requireNonNull(DefaultConnectorTypes.getSide(getItem().getType())).isVertical()) {
      addPoint((start.getX() + end.getX()) / 2, start.getY());
      addPoint((start.getX() + end.getX()) / 2, end.getY());
    } else {
      addPoint(start.getX(), (start.getY() + end.getY()) / 2);
      addPoint(end.getX(), (start.getY() + end.getY()) / 2);
    }

    addPoint(end);
  }

  /**
   * Draws the tail based additionally on the sides of the nodes it starts and ends at.
   *
   * @param start the start position of the tail
   * @param end the end position of the tail
   * @param target the connector the tail is attaching to
   */
  private void drawSmart(final Point2D start, final Point2D end, final @NotNull GConnector target) {

    clearPoints();
    addPoint(start);

    final Side startSide = DefaultConnectorTypes.getSide(getItem().getType());
    final Side endSide = DefaultConnectorTypes.getSide(target.getType());

    final List<Point2D> points =
        RectangularPathCreator.createPath(start, end, Objects.requireNonNull(startSide), endSide);
    points.forEach(this::addPoint);

    addPoint(end);
  }

  /** Clears all the points from the tail path. */
  private void clearPoints() {
    line.getPoints().clear();
  }

  /**
   * Adds the given point to the tail path.
   *
   * @param point the x & y coordinates of the point
   */
  private void addPoint(final @NotNull Point2D point) {
    addPoint(point.getX(), point.getY());
  }

  /**
   * Adds the given point to the tail path.
   *
   * @param x the x coordinate of the point
   * @param y the y coordinate of the point
   */
  private void addPoint(final double x, final double y) {
    line.getPoints().addAll(GeometryUtils.moveOffPixel(x), GeometryUtils.moveOffPixel(y));
  }

  @Override
  protected void selectionChanged(boolean isSelected) {
    // Not implemented
    throw new NotImplementedError();
  }
}
