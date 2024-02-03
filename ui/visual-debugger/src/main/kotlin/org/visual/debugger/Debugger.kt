package org.visual.debugger

import javafx.scene.Scene
import javafx.stage.WindowEvent
import org.visual.component.display.VisualStage
import org.visual.component.util.runOnFX
import org.visual.debugger.constant.FXMLKey
import org.visual.debugger.context.AttachSceneContext
import org.visual.debugger.context.DebuggerContext.load

class Debugger {
    private val rootScene by lazy { Scene(load(FXMLKey.LAYOUT)) }

    private val stage by lazy {
        VisualStage()
    }

    init {
        AttachSceneContext.stage.addListener { _, _, newValue ->
            run {
                newValue.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST) {
                    stage.close()
                }
            }
        }
    }

    fun showDebugger() {
        runOnFX {
            stage.scene = rootScene
            stage.showAndFocus()
        }
    }
}