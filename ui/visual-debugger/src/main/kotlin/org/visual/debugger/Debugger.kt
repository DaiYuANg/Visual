package org.visual.debugger

import javafx.scene.Scene
import org.visual.component.display.VisualStage
import org.visual.component.util.runOnFX
import org.visual.debugger.constant.FXMLKey
import org.visual.debugger.context.DebuggerContext.load

class Debugger(
    private val sceneId: Long
) {
    private val rootScene by lazy { Scene(load(FXMLKey.LAYOUT)) }

    private val stage by lazy {
        VisualStage()
            .apply {
                userData = sceneId
            }
    }

    fun showDebugger(){
        runOnFX {
            stage.scene = rootScene
            stage.showAndFocus()
        }
    }
}