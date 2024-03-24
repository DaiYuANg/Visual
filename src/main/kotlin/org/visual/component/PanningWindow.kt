package org.visual.component

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Orientation
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.control.ScrollBar
import javafx.scene.layout.Region
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Scale

open class PanningWindow : Region() {

  private val SCALE_MIN = 0.5f

  private val SCALE_MAX = 1.5f

  private val _content = SimpleObjectProperty(Region())

  private var content: Region
    get() {
      return _content.get()
    }
    set(value) {
      _content.set(value)
    }

  private val contentX: DoubleProperty =
      object : SimpleDoubleProperty() {
        override fun invalidated() {
          requestLayout()
        }
      }

  private val contentY: DoubleProperty =
      object : SimpleDoubleProperty() {
        override fun invalidated() {
          requestLayout()
        }
      }
  private val scrollX =
      ScrollBar().apply {
        orientation = Orientation.HORIZONTAL
        valueProperty().bindBidirectional(contentX)
        styleClass.add("graph-editor-scroll-bar")
      }
  private val scrollY =
      ScrollBar().apply {
        valueProperty().bindBidirectional(contentY)
        orientation = Orientation.VERTICAL
        styleClass.add("graph-editor-scroll-bar") // $NON-NLS-1$
      }

  private val clickPosition: Point2D? = null
  private val windowPosAtClick: Point2D? = null

  private val zoom: DoubleProperty = SimpleDoubleProperty(1.0)

  private val scale = Scale()

  init {
    val clip = Rectangle()
    clip.widthProperty().bind(widthProperty())
    clip.heightProperty().bind(heightProperty())
    setClip(clip)

    scale.xProperty().bind(zoom)
    scale.yProperty().bind(zoom)

    children.addAll(scrollX, scrollY)
  }

  override fun layoutChildren() {
    super.layoutChildren()
    val height = height
    val width = width
    val theContent: Node = content

    theContent.relocate(-contentX.get(), -contentY.get())

    val w = scrollY.width
    val h = scrollX.height

    scrollX.resizeRelocate(0.0, snapPositionY(height - h), snapSizeX(width - w), h)
    scrollY.resizeRelocate(snapPositionX(width - w), 0.0, w, snapSizeY(height - h))

    val zoomFactor = theContent.localToSceneTransform?.mxx ?: 1.0
    scrollX.min = 0.0
    scrollX.max = getMaxX()
    scrollX.visibleAmount = zoomFactor * width
    scrollY.min = 0.0
    scrollY.max = getMaxY()
    scrollY.visibleAmount = zoomFactor * height
  }

  private fun getMaxX(): Double {
    val theContent = content
    val zoomFactor = theContent.localToSceneTransform.mxx
    return zoomFactor * theContent.width - width
  }

  private fun getMaxY(): Double {
    val theContent = content
    val zoomFactor = theContent.localToSceneTransform.mxx
    return zoomFactor * theContent.height - height
  }
}
