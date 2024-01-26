package org.visual.model.component.util

import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import kotlin.math.ceil

private const val HALF_A_PIXEL = 0.5

fun mousePositionToPoint2D(event: MouseEvent, node: Node): Point2D {
  val sceneX = event.sceneX
  val sceneY = event.sceneY

  val containerScene = node.localToScene(0.0, 0.0)
  return Point2D(sceneX - containerScene.x, sceneY - containerScene.y)
}

fun moveOffPixel(position: Double): Double {
  return ceil(position) - HALF_A_PIXEL
}
