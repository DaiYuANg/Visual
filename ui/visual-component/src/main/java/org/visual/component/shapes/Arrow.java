package org.visual.component.shapes;

import static java.lang.Math.*;
import static org.visual.component.util.GeometryUtil.moveOffPixel;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 * An arrow shape.
 *
 * <p>This is a [Node] subclass and can be added to the JavaFX scene graph in the usual way. Styling
 * can be achieved via the CSS classes *arrow-line* and *arrow-head*.
 *
 * <p>Example:
 *
 * <pre>
 * `Arrow arrow = new Arrow();
 * arrow.setStart(10, 20);
 * arrow.setEnd(100, 150);
 * arrow.draw();`
 * </pre>
 */
public class Arrow extends Group {
  private final Line line = new Line();
  private final ArrowHead head = new ArrowHead();

  private double startX = 0.0;
  private double startY = 0.0;

  private double endX = 0.0;
  private double endY = 0.0;

  /** Creates a new [Arrow]. */
  public Arrow() {
    line.getStyleClass().add(STYLE_CLASS_LINE);
    head.getStyleClass().add(STYLE_CLASS_HEAD);

    getChildren().addAll(line, head);
  }

  /**
   * Sets the width of the arrow-head.
   *
   * @param width the width of the arrow-head
   */
  public void setHeadWidth(double width) {
    head.setWidth(width);
  }

  /**
   * Sets the length of the arrow-head.
   *
   * @param length the length of the arrow-head
   */
  public void setHeadLength(double length) {
    head.setLength(length);
  }

  /**
   * Sets the radius of curvature of the [ArcTo] at the base of the arrow-head.
   *
   * <p>If this value is less than or equal to zero, a straight line will be drawn instead. The
   * default is -1.
   *
   * @param radius the radius of curvature of the arc at the base of the arrow-head
   */
  public void setHeadRadius(double radius) {
    head.setRadiusOfCurvature(radius);
  }

  public Point2D getStart() {
    return new Point2D(startX, startY);
  }

  /**
   * Sets the start position of the arrow.
   *
   * @param pStartX the x-coordinate of the start position of the arrow
   * @param pStartY the y-coordinate of the start position of the arrow
   */
  public void setStart(double pStartX, double pStartY) {
    startX = pStartX;
    startY = pStartY;
  }

  public Point2D getEnd() {
    return new Point2D(endX, endY);
  }

  /**
   * Sets the end position of the arrow.
   *
   * @param pEndX the x-coordinate of the end position of the arrow
   * @param pEndY the y-coordinate of the end position of the arrow
   */
  public void setEnd(double pEndX, double pEndY) {
    endX = pEndX;
    endY = pEndY;
  }

  /** Draws the arrow for its current size and position values. */
  public void draw() {
    double deltaX = endX - startX;
    double deltaY = endY - startY;

    double angle = atan2(deltaX, deltaY);

    double headX = endX - head.getLength() / 2 * sin(angle);
    double headY = endY - head.getLength() / 2 * cos(angle);

    line.setStartX(moveOffPixel(startX));
    line.setStartY(moveOffPixel(startY));
    line.setEndX(moveOffPixel(headX));
    line.setEndY(moveOffPixel(headY));

    head.setCenter(headX, headY);
    head.setAngle(Math.toDegrees(-angle));
    head.draw();
  }

  private static final String STYLE_CLASS_LINE = "arrow-line";
  private static final String STYLE_CLASS_HEAD = "arrow-head";
}
