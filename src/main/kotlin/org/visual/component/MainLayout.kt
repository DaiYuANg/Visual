package org.visual.component

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.layout.VBox

@Singleton
class MainLayout @Inject constructor(globalMenu: GlobalMenu) : VBox() {
  init {
    children.addAll(globalMenu)
  }
}
