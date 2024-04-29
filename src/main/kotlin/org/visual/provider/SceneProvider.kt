package org.visual.provider

import jakarta.inject.Inject
import jakarta.inject.Provider
import javafx.scene.Scene
import org.visual.component.MainLayout

class SceneProvider @Inject constructor(private val pane: MainLayout) : Provider<Scene> {
  override fun get(): Scene {
    return Scene(pane).apply {}
  }
}
