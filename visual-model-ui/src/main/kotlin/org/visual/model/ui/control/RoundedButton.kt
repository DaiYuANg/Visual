package org.visual.model.ui.control

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class RoundedButton(buttonText: String) : Button() {
    private val circle: Circle

    private val label: Label

    init {
        this.circle = createCircle()
        this.label = createLabel(buttonText)
    }

    init {
        val stackPane = StackPane(circle, label)
        graphic = stackPane
        padding = Insets(5.0)
    }

    private fun createCircle(): Circle {
        val newCircle = Circle()
        newCircle.radius = 10.0
        newCircle.fill = Color.LIGHTGRAY
        newCircle.stroke = Color.BLACK
        return newCircle
    }

    private fun createLabel(text: String): Label {
        val newLabel = Label(text)
        newLabel.textFill = Color.BLACK
        return newLabel
    }
}
