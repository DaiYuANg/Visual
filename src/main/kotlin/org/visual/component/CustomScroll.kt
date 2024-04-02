package org.visual.component

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Orientation
import javafx.scene.control.ScrollBar
import javafx.scene.layout.Region

open class CustomScroll : Region() {

  protected val contentX: DoubleProperty =
      object : SimpleDoubleProperty() {
        override fun invalidated() {
          requestLayout()
        }
      }

  protected val contentY: DoubleProperty =
      object : SimpleDoubleProperty() {
        override fun invalidated() {
          requestLayout()
        }
      }

  private val scrollX =
      ScrollBar().apply {
        orientation = Orientation.HORIZONTAL
        valueProperty().bindBidirectional(contentX)
        min = 0.0
      }

  private val scrollY =
      ScrollBar().apply {
        valueProperty().bindBidirectional(contentY)
        orientation = Orientation.VERTICAL
        min = 0.0
      }

  init {
    children.addAll(scrollX, scrollY)
  }

  override fun layoutChildren() {
    super.layoutChildren()
    // scrollbars
    val height = height
    val width = width
    val w = scrollY.width
    val h = scrollX.height
    scrollX.resizeRelocate(0.0, snapPositionY(height - h), snapSizeX(width), h)
    scrollY.resizeRelocate(snapPositionX(width - w), 0.0, w, snapSizeY(height - h))
    scrollX.visibleAmount = 1 * width
    scrollX.min = 0.0
    scrollY.min = 0.0
    scrollY.visibleAmount = 1 * height
  }
}
