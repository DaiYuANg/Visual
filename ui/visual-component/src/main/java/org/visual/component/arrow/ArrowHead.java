package org.visual.component.arrow;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import lombok.Getter;
import lombok.Setter;

/** An arrow-head shape. This is used by the [Arrow] class. */
@Getter
@Setter
public class ArrowHead extends Path {
  private double x = 0.0;
  private double y = 0.0;

  public static final double DEFAULT_LENGTH = 10.0;
  public static final double DEFAULT_WIDTH = 10.0;

  public double length = DEFAULT_LENGTH;
  public double width = DEFAULT_WIDTH;
  public double radius = -1.0;

  private final Rotate r = new Rotate();

  /** Creates a new [ArrowHead]. */
  public ArrowHead() {
    setFill(Color.BLACK);
    setStrokeType(StrokeType.INSIDE);
    getTransforms().add(r);
  }

  /**
   * Sets the center position of the arrow-head.
   *
   * @param pX the x-coordinate of the center of the arrow-head
   * @param pY the y-coordinate of the center of the arrow-head
   */
  public void setCenter(double pX, double pY) {
    x = pX;
    y = pY;

    r.setPivotX(pX);
    r.setPivotY(pY);
  }

  /**
   * Sets the radius of curvature of the [ArcTo] at the base of the arrow-head.
   *
   * <p>If this value is less than or equal to zero, a straight line will be drawn instead. The
   * default is -1.
   *
   * @param pRadius the radius of curvature of the arc at the base of the arrow-head
   */
  public void setRadiusOfCurvature(double pRadius) {
    radius = pRadius;
  }

  /**
   * Sets the rotation angle of the arrow-head.
   *
   * @param angle the rotation angle of the arrow-head, in degrees
   */
  public void setAngle(double angle) {
    r.setAngle(angle);
  }

  /** Draws the arrow-head for its current size and position values. */
  public void draw() {
    getElements().clear();

    getElements().add(new MoveTo(x, y + length / 2));
    getElements().add(new LineTo(x + width / 2, y - length / 2));

    if (radius > 0) {
      ArcTo arcTo = new ArcTo();
      arcTo.setX(x - width / 2);
      arcTo.setY(y - length / 2);
      arcTo.setRadiusX(radius);
      arcTo.setRadiusY(radius);
      arcTo.setSweepFlag(true);
      getElements().add(arcTo);
    } else {
      getElements().add(new LineTo(x - width / 2, y - length / 2));
    }

    getElements().add(new ClosePath());
  }
}
