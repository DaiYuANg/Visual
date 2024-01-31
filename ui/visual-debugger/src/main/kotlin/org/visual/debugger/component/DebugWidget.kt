package org.visual.debugger.component

import javafx.scene.layout.StackPane
import org.visual.component.widget.FpsWidget

class DebugWidget : StackPane() {
  init {
    layoutX = 10.0
    layoutY = 10.0
    setPrefSize(50.0, 50.0)
    val fpsWidget = FpsWidget()
    children.add(fpsWidget)
    toFront()
  }
}
