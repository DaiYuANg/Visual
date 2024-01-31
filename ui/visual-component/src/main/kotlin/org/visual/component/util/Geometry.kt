package org.visual.component.util

import javafx.geometry.Insets
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Rectangle2D
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.stage.Screen
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

fun makeSameInsets(padding: Double): Insets {
  return Insets(padding, padding, padding, padding)
}

fun posToXy(pos: Pos): Pair<Double, Double> {
  val screen: Screen = Screen.getPrimary()
  val bounds: Rectangle2D = screen.visualBounds
  val centerX: Double = bounds.minX + (bounds.width / 2)
  when (pos) {
    Pos.TOP_LEFT -> {
      return Pair(0.0, 0.0)
    }
    Pos.CENTER -> {
      val centerY: Double = bounds.minY + (bounds.height / 2)
      return Pair(centerX, centerY)
    }
    Pos.TOP_RIGHT -> {
      return Pair(bounds.maxX, 0.0)
    }
    Pos.TOP_CENTER -> {
      return Pair(centerX, 0.0)
    }
    else -> return Pair(0.0, 0.0)
  }
}
