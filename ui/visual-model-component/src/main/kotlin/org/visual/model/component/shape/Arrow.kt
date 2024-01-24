package org.visual.model.component.shape

import javafx.scene.shape.Polygon
import javafx.scene.transform.Scale

class Arrow {

  private val arrow by lazy { createArrow() }

  private fun createArrow(): Polygon {
    return Polygon().apply {
      points.addAll(
          0.0, 0.0, 20.0, 10.0, 10.0, 10.0, 10.0, 20.0, -10.0, 20.0, -10.0, 10.0, -20.0, 10.0)
    }
  }

  fun setScale(scale: Double) {
    arrow.transforms.add(
        Scale(scale, scale),
    )
  }
}
