package org.visual.model.ui.util

import javafx.geometry.Rectangle2D
import javafx.stage.Screen

val primaryScreen: Rectangle2D = Screen.getPrimary().visualBounds

fun sizeOfScreen(percent: Double): Pair<Double, Double> {
    return (primaryScreen.width * 0.8) to
            (primaryScreen.height * 0.8)
}