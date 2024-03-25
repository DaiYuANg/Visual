package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.Event
import javafx.geometry.Orientation
import javafx.geometry.Point2D
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.control.ScrollBar
import javafx.scene.input.MouseEvent
import javafx.scene.input.ZoomEvent
import javafx.scene.layout.Region
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Scale
import kotlin.math.max
import kotlin.math.min

open class PanningWindow : Region() {

  private val SCALE_MIN = 0.5f

  private val SCALE_MAX = 1.5f

  private val log = KotlinLogging.logger {}

  private val _content by lazy { SimpleObjectProperty<Region>() }

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

  private var clickPosition: Point2D? = null
  private var windowPosAtClick: Point2D? = null

  private val zoom: DoubleProperty = SimpleDoubleProperty(1.0)

  private val scale = Scale()

  init {
    val clip = Rectangle()
    clip.widthProperty().bind(widthProperty())
    clip.heightProperty().bind(heightProperty())
    setClip(clip)
    scale.xProperty().bind(zoom)
    scale.yProperty().bind(zoom)
    children.addAll()
    children.addAll(scrollX, scrollY)
  }

  init {
    _content.addListener { _, _, newValue ->
      run {
        this@PanningWindow.children.removeFirst()
        this@PanningWindow.children.addFirst(newValue)
      }
    }
  }

  init {
    val initContent =
        Region().apply {
          addEventHandler(MouseEvent.MOUSE_PRESSED) {
            log.atInfo { message = "Mouse pressed" }
            startPanning(it.screenX, it.screenY)
          }

          addEventHandler(MouseEvent.MOUSE_DRAGGED) {
            if (Cursor.MOVE != cursor) {
              startPanning(it.screenX, it.screenY)
            }
            val deltaX: Double = it.screenX - clickPosition!!.x
            val deltaY: Double = it.screenY - clickPosition!!.y

            val newWindowX = windowPosAtClick!!.x - deltaX
            val newWindowY = windowPosAtClick!!.y - deltaY
            panTo(newWindowX, newWindowY)
          }

          addEventHandler(MouseEvent.MOUSE_RELEASED) { panningFinished(it) }

          addEventHandler(ZoomEvent.ANY) {}

          prefWidth = 1000.0
          prefHeight = 1000.0
          style = "-fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;"
        }
    _content.set(initContent)
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

  private fun canNotPan(): Boolean {
    return content.width < width
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

  private fun startPanning(x: Double, y: Double) {
    cursor = Cursor.CROSSHAIR
    log.atInfo { message = "start panning" }

    clickPosition = Point2D(x, y)
    windowPosAtClick = Point2D(contentX.get(), contentY.get())
  }

  private fun panningFinished(event: Event) {
    cursor = null
    event.consume()
  }

  private fun panTo(x: Double, y: Double) {
    if (canNotPan()) {
      return
    }

    val newX: Double = checkContentX(x)
    val newY: Double = checkContentY(y)
    if (newX != contentX.get() || newY != contentY.get()) {
      contentX.set(newX)
      contentY.set(newY)
    }
  }

  private fun checkContentX(xToCheck: Double): Double {
    return snapPositionX(min(getMaxX(), max(xToCheck, 0.0)))
  }

  private fun checkContentY(yToCheck: Double): Double {
    return snapPositionY(min(getMaxY(), max(yToCheck, 0.0)))
  }
}
