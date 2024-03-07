package org.visual.component.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The drag handler<br>
 * </br> Usage example:
 *
 * <pre>
 * var handler = new DragHandler() {
 * // ... override ...
 * };
 * node.setOnMousePressed(handler);
 * node.setOnMouseDragged(handler);
 * </pre>
 *
 * *
 */
public abstract class DragHandler implements EventHandler<MouseEvent> {
  private double oldNodeX = 0.0;
  private double oldNodeY = 0.0;
  private double oldOffsetX = 0.0;
  private double oldOffsetY = 0.0;

  protected abstract void set(double x, double y);

  protected abstract double[] get();

  private double[] getOffset(MouseEvent e) {
    return new double[] {e.getScreenX(), e.getScreenY()};
  }

  @Override
  public void handle(MouseEvent e) {
    if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
      pressed(e);
    } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
      dragged(e);
      consume(e);
    }
  }

  protected void consume(MouseEvent e) {
    // do not consume
  }

  /**
   * The function to run when pressed
   *
   * @param e mouse event
   */
  protected void pressed(MouseEvent e) {
    double[] xy = get();
    this.oldNodeX = xy[0];
    this.oldNodeY = xy[1];
    double[] offsetXy = getOffset(e);
    oldOffsetX = offsetXy[0];
    oldOffsetY = offsetXy[1];
  }

  /**
   * The function to run when dragged
   *
   * @param e mouse event
   */
  protected void dragged(MouseEvent e) {
    double[] offxy = getOffset(e);
    double deltaX = offxy[0] - this.oldOffsetX;
    double deltaY = offxy[1] - this.oldOffsetY;
    double x = calculateDeltaX(deltaX, deltaY) + this.oldNodeX;
    double y = calculateDeltaY(deltaX, deltaY) + this.oldNodeY;
    set(x, y);
  }

  /**
   * Calculate actual delta X to apply
   *
   * @param deltaX raw deltaX
   * @param deltaY raw deltaY
   * @return deltaX to apply
   */
  protected double calculateDeltaX(double deltaX, double deltaY) {
    return deltaX;
  }

  /**
   * Calculate actual delta Y to apply
   *
   * @param deltaX raw deltaX
   * @param deltaY raw deltaY
   * @return deltaY to apply
   */
  protected double calculateDeltaY(double deltaX, double deltaY) {
    return deltaY;
  }
}
