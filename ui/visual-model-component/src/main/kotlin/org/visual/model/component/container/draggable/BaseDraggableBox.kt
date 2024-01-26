package org.visual.model.component.container.draggable

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.layout.StackPane

/** Base Draggable Box Common Property */
abstract class BaseDraggableBox : StackPane() {
  protected val DEFAULT_ALIGNMENT_THRESHOLD = 5.0

  private val _boundValue = SimpleDoubleProperty(15.0)
  var boundValue: Double
    get() = _boundValue.get()
    set(value) = _boundValue.set(value)

  private val _lastLayoutX = SimpleDoubleProperty(0.0)
  var lastLayoutX: Double
    get() = _lastLayoutX.get()
    set(value) = _lastLayoutX.set(value)

  private val _lastLayoutY = SimpleDoubleProperty(0.0)
  var lastLayoutY: Double
    get() = _lastLayoutY.get()
    set(value) = _lastLayoutY.set(value)

  private val _lastMouseX = SimpleDoubleProperty(0.0)
  var lastMouseX: Double
    get() = _lastMouseX.get()
    set(value) = _lastMouseX.set(value)

  private val _lastMouseY = SimpleDoubleProperty(0.0)
  var lastMouseY: Double
    get() = _lastMouseY.get()
    set(value) = _lastMouseY.set(value)

  init {
    isPickOnBounds = false
  }

  protected fun storeClickValuesForDrag(pX: Double, pY: Double) {
    _lastLayoutX.set(layoutX)
    _lastLayoutY.set(layoutY)

    _lastMouseX.set(pX)
    _lastMouseY.set(pY)
  }
}
