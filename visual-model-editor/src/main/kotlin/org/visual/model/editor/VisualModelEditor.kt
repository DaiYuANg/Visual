package org.visual.model.editor

import com.sun.javafx.fxml.FXMLLoaderHelper
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.slf4j.LoggerFactory

class VisualModelEditor : Application() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun init() {
        log.info("virtual")
    }

    override fun start(p0: Stage?) {
        FXMLLoader().load<Parent>()
        val root = Pane()
        p0?.apply {
            title = "My first chart!"
            scene = (Scene(root, width, height))
            show()
        }
    }

}

fun main() {
    Application.launch(VisualModelEditor::class.java)
}