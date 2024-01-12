package org.visual.model.ui.window

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.collections.ObservableList
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

open class PanningWindow : Region() {
    private val contentX: DoubleProperty = SimpleDoubleProperty(0.0)
    private val contentY: DoubleProperty = SimpleDoubleProperty(0.0)
    private val zoom: DoubleProperty = SimpleDoubleProperty(1.0)

    init {
        // Set up initial properties
        isManaged = false
        style = "-fx-background-color: white;"

        // Add a sample content (you can replace this with your own content)
        val sampleContent = Rectangle(2000.0, 2000.0, Color.AQUA)
        sampleContent.relocate(0.0, 0.0)
        children.add(sampleContent)

        // Add event handlers
        setOnMousePressed { handleMousePressed(it) }
        setOnMouseDragged { handleMouseDragged(it) }
        setOnScroll { handleScroll(it) }
    }

    public override fun getChildren(): ObservableList<Node> {
        return super.getChildren()
    }

    private fun handleMousePressed(event: MouseEvent) {
        scene?.let {
            it.cursor = Cursor.MOVE
            contentX.set(contentX.get() - (event.sceneX - contentX.get()))
            contentY.set(contentY.get() - (event.sceneY - contentY.get()))
        }
    }

    private fun handleMouseDragged(event: MouseEvent) {
        scene?.let {
            val deltaX = event.sceneX - contentX.get()
            val deltaY = event.sceneY - contentY.get()
            contentX.set(event.sceneX)
            contentY.set(event.sceneY)
            relocate(relocateX(deltaX), relocateY(deltaY))
        }
    }

    private fun handleScroll(event: ScrollEvent) {
        val zoomFactor = if (event.deltaY > 0) 1.1 else 0.9
        zoom.set(zoom.get() * zoomFactor)
        val delta = sceneToLocal(event.sceneX, event.sceneY)
        contentX.set(contentX.get() - delta.x * (zoomFactor - 1))
        contentY.set(contentY.get() - delta.y * (zoomFactor - 1))
        relocate(relocateX(delta.x * (zoomFactor - 1)), relocateY(delta.y * (zoomFactor - 1)))
    }

    private fun relocateX(deltaX: Double): Double {
        val newX = contentX.get() + deltaX
        val maxX = 0.0.coerceAtLeast(newX.coerceAtMost(width - scaledContentWidth()))
        return if (maxX == 0.0 || maxX == width - scaledContentWidth()) contentX.get() else newX
    }

    private fun relocateY(deltaY: Double): Double {
        val newY = contentY.get() + deltaY
        val maxY = 0.0.coerceAtLeast(newY.coerceAtMost(height - scaledContentHeight()))
        return if (maxY == 0.0 || maxY == height - scaledContentHeight()) contentY.get() else newY
    }

    private fun scaledContentWidth(): Double {
        return width / zoom.get()
    }

    private fun scaledContentHeight(): Double {
        return height / zoom.get()
    }
}
