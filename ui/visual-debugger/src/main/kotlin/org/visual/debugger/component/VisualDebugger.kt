package org.visual.debugger.component

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Region
import org.visual.debugger.Debugger

class VisualDebugger : Region() {
    private val keyCombination: KeyCombination = KeyCodeCombination(KeyCode.F12, KeyCombination.CONTROL_DOWN)

    init {
        translateX = 50.0
        translateY = 50.0
        width = 50.0
        height = 50.0
        isManaged = false
        isVisible = false
    }

    init {
        sceneProperty().addListener { _, _, newScene ->
            run {
                newScene.accelerators[keyCombination] = Runnable {
                    Debugger(newScene).showDebugger()
                }
            }
        }
    }
}
