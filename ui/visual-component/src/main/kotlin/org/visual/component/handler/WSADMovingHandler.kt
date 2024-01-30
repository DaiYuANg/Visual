package org.visual.component.handler

import javafx.animation.AnimationTimer
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

/**
 * <pre>
 * pane.setOnKeyPressed(wsadHandler);
 * pane.setOnKeyReleased(wsadHandler);
 * </pre> *
 */
abstract class WSADMovingHandler(private val xSpeed: Double, private val ySpeed: Double) :
    EventHandler<KeyEvent> {
  private var time: Long = 0
  private var w = false
  private var s = false
  private var wsIsW = false
  private var a = false
  private var d = false
  private var adIsA = false
  private var timer: AnimationTimer? = null
  private var x = 0.0
  private var y = 0.0

  override fun handle(event: KeyEvent) {
    if (event.eventType == KeyEvent.KEY_PRESSED) {
      onPressed(event.code)
    } else if (event.eventType == KeyEvent.KEY_RELEASED) {
      onReleased(event.code)
    }
  }

  private fun onPressed(code: KeyCode) {
    when (code) {
      KeyCode.W ->
          if (!w) {
            w = true
            wsIsW = true
            record()
          }
      KeyCode.S ->
          if (!s) {
            s = true
            wsIsW = false
            record()
          }
      KeyCode.A ->
          if (!a) {
            a = true
            adIsA = true
            record()
          }
      KeyCode.D ->
          if (!d) {
            d = true
            adIsA = false
            record()
          }
      else -> return
    }
    if (w || s || a || d) {
      if (timer == null) {
        timer = Timer()
        timer!!.start()
      }
    }
  }

  private fun record() {
    time = System.nanoTime()
    val xy = get()
    x = xy[0]
    y = xy[1]
  }

  private fun onReleased(code: KeyCode) {
    when (code) {
      KeyCode.W ->
          if (w) {
            w = false
            wsIsW = false
            record()
          }
      KeyCode.S ->
          if (s) {
            s = false
            wsIsW = true
            record()
          }
      KeyCode.A ->
          if (a) {
            a = false
            adIsA = false
            record()
          }
      KeyCode.D ->
          if (d) {
            d = false
            adIsA = true
            record()
          }
      else -> return
    }
    if (!w && !s && !a && !d) {
      val timer = this.timer
      this.timer = null
      timer?.stop()
    }
    if (!w && !s && !a && !d) {
      time = 0
    }
  }

  private inner class Timer : AnimationTimer() {
    override fun handle(now: Long) {
      if (time == 0L) {
        return
      }
      val delta = now - time
      if (delta < 0) return
      var x = this@WSADMovingHandler.x
      var y = this@WSADMovingHandler.y
      if (w || s) {
        if (wsIsW) {
          y -= ySpeed * delta / 1000000
        } else {
          y += ySpeed * delta / 1000000
        }
      }
      if (a || d) {
        if (adIsA) {
          x -= xSpeed * delta / 1000000
        } else {
          x += xSpeed * delta / 1000000
        }
      }
      set(x, y)
    }
  }

  protected abstract fun set(x: Double, y: Double)

  protected abstract fun get(): DoubleArray
}
