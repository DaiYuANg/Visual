package org.visual.component.display.container;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.StackPane;

/** Base Draggable Box Common Property */
public abstract class BaseDraggableBox extends StackPane {
  protected final double DEFAULT_ALIGNMENT_THRESHOLD = 5.0;

  private final SimpleDoubleProperty boundValue = new SimpleDoubleProperty(15.0);

  public double getBoundValue() {
    return boundValue.get();
  }

  public void setBoundValue(double value) {
    boundValue.set(value);
  }

  private final SimpleDoubleProperty lastLayoutX = new SimpleDoubleProperty(0.0);

  public double getLastLayoutX() {
    return lastLayoutX.get();
  }

  public void setLastLayoutX(double value) {
    lastLayoutX.set(value);
  }

  private final SimpleDoubleProperty lastLayoutY = new SimpleDoubleProperty(0.0);

  public double getLastLayoutY() {
    return lastLayoutY.get();
  }

  public void setLastLayoutY(double value) {
    lastLayoutY.set(value);
  }

  private final SimpleDoubleProperty lastMouseX = new SimpleDoubleProperty(0.0);

  public double getLastMouseX() {
    return lastMouseX.get();
  }

  public void setLastMouseX(double value) {
    lastMouseX.set(value);
  }

  private final SimpleDoubleProperty lastMouseY = new SimpleDoubleProperty(0.0);

  public double getLastMouseY() {
    return lastMouseY.get();
  }

  public void setLastMouseY(double value) {
    lastMouseY.set(value);
  }

  public BaseDraggableBox() {
    setPickOnBounds(false);
  }

  protected void storeClickValuesForDrag(double pX, double pY) {
    lastLayoutX.set(getLayoutX());
    lastLayoutY.set(getLayoutY());

    lastMouseX.set(pX);
    lastMouseY.set(pY);
  }
}
