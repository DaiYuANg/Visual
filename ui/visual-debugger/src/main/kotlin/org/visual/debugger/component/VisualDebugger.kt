package org.visual.debugger.component

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.visual.component.util.makeSameInsets
import org.visual.component.util.posToXy
import org.visual.debugger.Debugger
import org.visual.debugger.context.AttachSceneContext
import org.visual.debugger.view.VisualDebuggerView
import org.visual.shared.KSlf4j
import org.visual.shared.util.AutoIncrement

@KSlf4j
class VisualDebugger : Pane() {
  private val keyCombination: KeyCombination by lazy { KeyCodeCombination(KeyCode.F12) }

  private val _show by lazy { SimpleBooleanProperty(false) }
  var show: Boolean?
    get() = _show.get()
    set(value) {
      value?.let { _show.set(it) }
    }

  private val _pos by lazy { SimpleObjectProperty(Pos.TOP_LEFT) }

  var pos: Pos?
    get() = _pos.get()
    set(value) {
      _pos.set(value)
    }

  private var xOffset = 0.0
  private var yOffset = 0.0

  init {
    isManaged = false
    isVisible = true
    padding = makeSameInsets(0.5)
    children.add(DebugWidget())
  }

  init {

    sceneProperty().addListener { _, _, newScene ->
      run {
        newScene.windowProperty().addListener { _, _, t2 ->
          run {
            val debugger = setupAttachScene(t2 as Stage)
            newScene.accelerators[keyCombination] = Runnable { debugger.showDebugger() }
            if (_show.get()) {
              VisualDebuggerView.show(newScene)
              debugger.showDebugger()
            }
            toFront()
          }
        }
      }
    }
  }

  private fun setupAttachScene(stage: Stage): Debugger {
    val sceneId = AutoIncrement.next()
    AttachSceneContext.stage.set(stage)
    val debugger = Debugger(sceneId)
    return debugger
  }

  init {
    _pos.addListener { _, _, newValue ->
      run {
        val (x, y) = posToXy(newValue)
        translateX = x - width
        translateY = y
        toFront()
      }
    }
  }

  init {
    setOnMouseEntered { cursor = Cursor.HAND }
    setOnMousePressed { event ->
      xOffset = event.x
      yOffset = event.y
    }

    setOnMouseDragged { event ->
      translateX = event.sceneX - xOffset
      translateY = event.sceneY - yOffset
    }
  }
}
