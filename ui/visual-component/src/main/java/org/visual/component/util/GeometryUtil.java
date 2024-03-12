package org.visual.component.util;

import static java.lang.Math.ceil;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.util.Pair;

public class GeometryUtil {
  private static final double HALF_A_PIXEL = 0.5;

  public static Point2D mousePositionToPoint2D(MouseEvent event, Node node) {
    double sceneX = event.getSceneX();
    double sceneY = event.getSceneY();

    Point2D containerScene = node.localToScene(0.0, 0.0);
    return new Point2D(sceneX - containerScene.getX(), sceneY - containerScene.getY());
  }

  /**
   * Moves an x or y position value off-pixel.
   *
   * <p>This is for example useful for a 1-pixel-wide stroke with a stroke-type of centered. The x
   * and y positions need to be off-pixel so that the stroke is on-pixel.
   *
   * @param position the position to move off-pixel
   * @return the position moved to the nearest value halfway between two integers
   */
  public static double moveOffPixel(double position) {
    return ceil(position) - HALF_A_PIXEL;
  }

  public static Insets makeSameInsets(double padding) {
    return new Insets(padding, padding, padding, padding);
  }

  public static Pair<Double, Double> posToXy(Pos pos) {
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    double centerX = bounds.getMinX() + (bounds.getWidth() / 2);

    return switch (pos) {
      case CENTER -> {
        double centerY = bounds.getMinY() + (bounds.getHeight() / 2);
        yield new Pair<>(centerX, centerY);
      }
      case TOP_RIGHT -> new Pair<>(bounds.getMaxX(), 0.0);
      case TOP_CENTER -> new Pair<>(centerX, 0.0);
      default -> new Pair<>(0.0, 0.0);
    };
  }
}
