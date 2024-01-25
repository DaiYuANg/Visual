package org.visual.model.component.display

import java.util.function.Supplier
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Insets
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.stage.Stage
import org.visual.model.component.title.TitleBar
import org.visual.model.component.util.ScreenUtil
import org.visual.model.shared.OS

abstract class CommonTitleBar : HBox(), TitleBar {
  private val xOffset = SimpleDoubleProperty(0.0)
  private val yOffset = SimpleDoubleProperty(0.0)

  private var prevWidth = 0.0
  private var prevHeight = 0.0

  private val stage = Supplier { scene.window as Stage }

  init {
    padding = Insets(0.0, 0.0, 0.0, 0.0)
  }

  init {
    addEventHandler(MouseEvent.MOUSE_DRAGGED) { e: MouseEvent ->
      scene.window.x = e.sceneX - xOffset.get()
      scene.window.y = e.sceneY - xOffset.get()
    }
    addEventHandler(MouseEvent.MOUSE_PRESSED) { e: MouseEvent ->
      scene.window.opacity = 0.5
      xOffset.set(e.sceneX)
      yOffset.set(e.sceneY)
    }
    addEventHandler(MouseEvent.MOUSE_RELEASED) { e: MouseEvent? -> scene.window.opacity = 1.0 }
    addEventHandler(KeyEvent.KEY_TYPED) { e: KeyEvent ->
      if (e.code.isFunctionKey && e.code == KeyCode.F11) {
        max(scene.window as Stage)
      }
    }
  }

  override fun close() {
    if (OS.OS == OS.MAC) {
      stage.get().isIconified = true
      return
    }
    stage.get().close()
  }

  override fun maximize() {
    max(stage.get())
  }

  override fun minimize() {
    stage.get().isIconified = true
  }

  fun restoreSizeOrMax() {
    if (prevWidth == 0.0 && prevHeight == 0.0) {
      prevWidth = scene.width
      prevHeight = scene.height
      max(stage.get())
    } else {
      stage.get().width = prevWidth
      stage.get().height = prevHeight
    }
  }

  fun max(stage: Stage) {
    stage.x = ScreenUtil.primaryScreen.minX
    stage.y = ScreenUtil.primaryScreen.minY
    stage.width = ScreenUtil.primaryScreen.width
    stage.height = ScreenUtil.primaryScreen.height
  }
}
