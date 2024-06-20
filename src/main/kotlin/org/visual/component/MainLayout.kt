package org.visual.component

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

@Singleton
class MainLayout @Inject constructor(globalMenu: GlobalMenu) : VBox() {

  private val content =
      BorderPane().apply {
        top = ToolBar().apply {}

        center = Button("test")
      }

  init {
    children.addAll(globalMenu, content)
  }
}
