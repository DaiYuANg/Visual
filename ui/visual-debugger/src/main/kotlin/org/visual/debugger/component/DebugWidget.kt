package org.visual.debugger.component

import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import org.visual.component.util.makeSameInsets
import org.visual.component.widget.FpsWidget

class DebugWidget : StackPane() {

  private val selfBackground by lazy {
    val backgroundFill = BackgroundFill(Color.rgb(0, 0, 0, 0.5), null, makeSameInsets(0.5))
    Background(backgroundFill)
  }

  init {
    val fpsWidget = FpsWidget()
    children.add(fpsWidget)
    background = selfBackground
    toFront()
  }
}
