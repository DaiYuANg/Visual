/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.component.util;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

/** Utility class containing helper methods relating to geometry, positions, etc. */
public class GeometryUtils {

  private static final double HALF_A_PIXEL = 0.5;

  /**
   * Moves an x or y position value off-pixel.
   *
   * <p>This is for example useful for a 1-pixel-wide stroke with a stroke-type of centered. The x
   * and y positions need to be off-pixel so that the stroke is on-pixel.
   *
   * @param position the position to move off-pixel
   * @return the position moved to the nearest value halfway between two integers
   */
  public static double moveOffPixel(final double position) {
    return Math.ceil(position) - HALF_A_PIXEL;
  }

  public static @NotNull Point2D mousePositionToPoint2D(
      final @NotNull MouseEvent event, final @NotNull Node node) {
    final double sceneX = event.getSceneX();
    final double sceneY = event.getSceneY();

    final Point2D containerScene = node.localToScene(0, 0);
    return new Point2D(sceneX - containerScene.getX(), sceneY - containerScene.getY());
  }
}
