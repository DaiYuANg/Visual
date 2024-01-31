package org.visual.debugger.component

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Region
import org.visual.debugger.Debugger
import org.visual.debugger.context.AttachSceneContext
import java.util.UUID

class VisualDebugger : Region() {
    private val keyCombination: KeyCombination = KeyCodeCombination(KeyCode.F12)

    init {
        isManaged = false
        isVisible = false
    }

    init {
        sceneProperty().addListener { _, _, newScene ->
            run {
                val sceneId = UUID.randomUUID().node()
                AttachSceneContext.scene[sceneId] = newScene
                newScene.accelerators[keyCombination] = Runnable {
                    Debugger(sceneId).showDebugger()
                }
            }
        }
    }
}
