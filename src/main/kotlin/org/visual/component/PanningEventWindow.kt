package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.beans.property.SimpleObjectProperty
import javafx.event.Event
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Cursor
import javafx.scene.input.InputEvent
import javafx.scene.input.MouseEvent
import javafx.scene.input.ScrollEvent
import javafx.scene.input.TouchEvent
import javafx.scene.layout.Region
import javafx.scene.shape.Rectangle
import kotlin.math.max
import kotlin.math.min

open class PanningEventWindow : ZoomSupportWindow() {

  private val log = KotlinLogging.logger {}

  private val _content by lazy { SimpleObjectProperty<Region?>() }

  var content: Region?
    get() {
      return _content.get()
    }
    set(value) {
      _content.set(value)
    }

  private var clickPosition: Point2D? = null
  private var windowPosAtClick: Point2D? = null

  private val clip =
      Rectangle().apply {
        widthProperty().bind(this@PanningEventWindow.widthProperty())
        heightProperty().bind(this@PanningEventWindow.heightProperty())
      }

  private val events by lazy {
    mapOf(
        MouseEvent.MOUSE_PRESSED to
            EventHandler<InputEvent> { event -> handlePanningMousePressed(event as MouseEvent) },
        MouseEvent.MOUSE_DRAGGED to
            EventHandler<InputEvent> { event -> handlePanningMouseDragged(event as MouseEvent) },
        MouseEvent.MOUSE_RELEASED to
            EventHandler<InputEvent> { event -> handlePanningFinished(event) },
        TouchEvent.TOUCH_PRESSED to
            EventHandler<InputEvent> { event -> handlePanningTouchPressed(event as TouchEvent) },
        TouchEvent.TOUCH_MOVED to
            EventHandler<InputEvent> { event -> handlePanningTouchDragged(event as TouchEvent) },
        TouchEvent.TOUCH_RELEASED to
            EventHandler<InputEvent> { event -> handlePanningFinished(event) },
        ScrollEvent.SCROLL_STARTED to
            EventHandler<InputEvent> { event -> handleTrackpadStart(event as ScrollEvent) },
        ScrollEvent.SCROLL to
            EventHandler<InputEvent> { event -> handleTrackpadMove(event as ScrollEvent) },
        ScrollEvent.SCROLL_FINISHED to
            EventHandler<InputEvent> { event -> handlePanningFinished(event) })
  }

  private val onMoveCursor = Cursor.CROSSHAIR

  init {
    setClip(clip)
  }

  init {
    _content.addListener { _, old, new ->
      run {
        old?.let {
          events.forEach { (t, u) -> it.removeEventFilter(t, u) }
          this@PanningEventWindow.children.remove(old)
        }

        log.atInfo { message = "Content add :${new}" }

        new?.let {
          this@PanningEventWindow.children.add(it)
          events.forEach { (t, u) -> it.addEventHandler(t, u) }
        }
      }
    }
  }

  override fun layoutChildren() {
    super.layoutChildren()
    content?.relocate(-contentX.get(), -contentY.get())
  }

  fun panTo(x: Double, y: Double) {
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

  private fun getMaxX(): Double {
    val theContent = content
    if (theContent != null) {
      val zoomFactor = theContent.localToSceneTransform.mxx
      return zoomFactor * theContent.width - width
    }
    return 0.0
  }

  private fun getMaxY(): Double {
    val theContent = content
    if (theContent != null) {
      val zoomFactor = theContent.localToSceneTransform.mxx
      return zoomFactor * theContent.height - height
    }
    return 0.0
  }

  private fun handlePanningMousePressed(event: MouseEvent) {
    startPanning(event.screenX, event.screenY)
  }

  private fun handlePanningMouseDragged(event: MouseEvent) {
    if (onMoveCursor != cursor) {
      startPanning(event.screenX, event.screenY)
    }

    val deltaX = event.screenX - clickPosition!!.x
    val deltaY = event.screenY - clickPosition!!.y

    val newWindowX = windowPosAtClick!!.x - deltaX
    val newWindowY = windowPosAtClick!!.y - deltaY

    panTo(newWindowX, newWindowY)
  }

  private fun handlePanningFinished(event: Event) {
    cursor = null
    event.consume()
  }

  private fun handlePanningTouchPressed(event: TouchEvent) {
    startPanning(event.touchPoint.screenX, event.touchPoint.screenY)
  }

  private fun handlePanningTouchDragged(event: TouchEvent) {
    if (onMoveCursor != cursor) {
      startPanning(event.touchPoint.screenX, event.touchPoint.screenY)
    }

    val deltaX = event.touchPoint.screenX - clickPosition!!.x
    val deltaY = event.touchPoint.screenY - clickPosition!!.y

    val newWindowX = windowPosAtClick!!.x - deltaX
    val newWindowY: Double = windowPosAtClick!!.y - deltaY

    panTo(newWindowX, newWindowY)
  }

  private fun handleTrackpadStart(e: ScrollEvent) {
    log.atInfo { message = "x:${e.x},y:${e.deltaY}" }
    startPanning(e.x, e.y)
  }

  private fun handleTrackpadMove(e: ScrollEvent) {
    if (onMoveCursor != cursor) {
      startPanning(e.x, e.y)
    }

    val deltaX = e.x - clickPosition!!.x
    val deltaY = e.y - clickPosition!!.y

    val newWindowX = windowPosAtClick!!.x - deltaX
    val newWindowY: Double = windowPosAtClick!!.y - deltaY

    panTo(newWindowX, newWindowY)
  }

  private fun startPanning(x: Double, y: Double) {
    cursor = onMoveCursor
    clickPosition = Point2D(x, y)
    windowPosAtClick = Point2D(contentX.get(), contentX.get())
  }
}
