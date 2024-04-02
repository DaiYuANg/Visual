package org.visual.component

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.transform.Scale

open class ZoomSupportWindow : CustomScroll() {

  private val zoom: DoubleProperty = SimpleDoubleProperty(1.0)

  private val scale =
      Scale().apply {
        xProperty().bind(zoom)
        yProperty().bind(zoom)
      }
}
