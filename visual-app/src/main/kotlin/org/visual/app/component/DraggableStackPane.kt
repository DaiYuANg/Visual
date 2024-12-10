package org.visual.app.component

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.scene.Cursor
import javafx.scene.layout.StackPane

class DraggableStackPane : StackPane() {
  private val logger = KotlinLogging.logger {}
  private val dragAnchor = DoubleArray(2)

  init {
    setOnMouseEntered {
      cursor = Cursor.CROSSHAIR
    }
  }

  init {
    setOnMousePressed { event ->
      dragAnchor[0] = event.sceneX - layoutX
      dragAnchor[1] = event.sceneY - layoutY
    }

    setOnMouseDragged { event ->
      val newX = event.sceneX - dragAnchor[0]
      val newY = event.sceneY - dragAnchor[1]

      // 设置 StackPane 新的布局位置
      layoutX = newX
      layoutY = newY
    }
  }

}