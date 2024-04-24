package org.visual.provider

import jakarta.inject.Provider
import javafx.geometry.Insets
import javafx.scene.Scene
import org.visual.dsl.SceneBuilder

class SceneProvider : Provider<Scene> {
  override fun get(): Scene {
    val scene =
        SceneBuilder().scene {
          width(500.0)
          height(500.0)
          vbox(spacing = 10.0, padding = Insets(10.0)) {
            spacing = 10.0
            padding = Insets(10.0)
            button("OK") { onAction { println("OK button clicked") } }
            button("Cancel") { onAction { println("Cancel button clicked") } }
          }
        }
    return scene
  }
}
